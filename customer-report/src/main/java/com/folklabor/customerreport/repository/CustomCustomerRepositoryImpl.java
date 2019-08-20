package com.folklabor.customerreport.repository;

import com.folklabor.customerreport.utils.EmptyPage;
import com.folklabor.customerreport.utils.TimeUtils;
import com.folklabor.domain.Customer;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.script.Script;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.aggregations.metrics.avg.Avg;
import org.elasticsearch.search.aggregations.metrics.avg.AvgAggregationBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.data.util.CloseableIterator;
import org.springframework.stereotype.Repository;

import java.util.Date;

import static org.elasticsearch.index.query.QueryBuilders.rangeQuery;

@Slf4j
@Repository
public class CustomCustomerRepositoryImpl implements CustomCustomerRepository {
    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    private static final String customerIndex = "customers";
    private static final String customerType = "customer";
    private static final String birthdayFieldName = "dateOfBirth";

    @Override
    public CloseableIterator<Customer> streamCustomersBornBetween(Date start, Date end) {
        SearchQuery birthdaySearch = new NativeSearchQueryBuilder()
                .withQuery(rangeQuery(birthdayFieldName)
                        .from(start.getTime())
                        .to(end.getTime()))
                .withIndices(customerIndex)
                .withTypes(customerType)
                .withPageable(PageRequest.of(0, 10))
                .build();

        return elasticsearchTemplate.stream(birthdaySearch, Customer.class);
    }

    @Override
    public Long averageCustomersBornBetween(Date start, Date end) {
        String averageAgeAggregationName = "avgTimeDifference";
        Script dobAgeCalculator = new Script("Math.abs(System.currentTimeMillis() - doc['dateOfBirth'].date.getMillis())");

        AvgAggregationBuilder averageAgeAggregation = AggregationBuilders.avg(averageAgeAggregationName).script(dobAgeCalculator);
        SearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withQuery(rangeQuery(birthdayFieldName)
                        .from(start.getTime())
                        .to(end.getTime()))
                .withIndices(customerIndex)
                .withTypes(customerType)
                .addAggregation(averageAgeAggregation)
                .withPageable(EmptyPage.INSTANCE)
                .build();

        double averageAgeInMilliseconds = elasticsearchTemplate.query(searchQuery, searchResponse -> {
            Aggregations aggregations = searchResponse.getAggregations();
            Avg avgAggregation = aggregations.get(averageAgeAggregationName);
            return avgAggregation.getValue();
        });

        return TimeUtils.millisecondsToYears(averageAgeInMilliseconds);
    }
}

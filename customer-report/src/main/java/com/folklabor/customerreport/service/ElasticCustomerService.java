package com.folklabor.customerreport.service;

import com.folklabor.customerreport.repository.CustomerRepository;
import com.folklabor.customerreport.utils.MathUtils;
import com.folklabor.domain.Customer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.CloseableIterator;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Slf4j
@Service
public class ElasticCustomerService implements CustomerService {
    @Autowired
    private CustomerRepository repository;

    @Override
    public Customer save(Customer customer) {
        return repository.save(customer);
    }

    @Override
    public double findMedianAgeOfCustomersBornBetween(Date start, Date end) {
        Set<Double> ages = new HashSet<>();
        double medianAge = 0;

        try(CloseableIterator<Customer> customerStream = repository.streamCustomersBornBetween(start, end)) {
            while (customerStream.hasNext()) {
                Customer customer = customerStream.next();
                ages.add(Double.valueOf(customer.calculateYearsAlive()));
            }
        }

        if(!ages.isEmpty()){
            medianAge = MathUtils.medianOf(ages);
        }
        return medianAge;
    }

    @Override
    public double findAverageAgeOfCustomersBornBetween(Date start, Date end) {
        return repository.averageCustomersBornBetween(start, end);
    }
}

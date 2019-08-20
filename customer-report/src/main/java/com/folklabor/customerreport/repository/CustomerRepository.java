package com.folklabor.customerreport.repository;

import com.folklabor.domain.Customer;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface CustomerRepository extends ElasticsearchRepository<Customer, String>, CustomCustomerRepository {
}
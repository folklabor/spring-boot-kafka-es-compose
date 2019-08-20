package com.folklabor.customerreport.service;

import com.folklabor.domain.Customer;

import java.util.Date;

public interface CustomerService {
    Customer save(Customer customer);
    double findMedianAgeOfCustomersBornBetween(Date start, Date end);
    double findAverageAgeOfCustomersBornBetween(Date start, Date end);
}

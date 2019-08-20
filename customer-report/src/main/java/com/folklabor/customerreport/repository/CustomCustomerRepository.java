package com.folklabor.customerreport.repository;

import com.folklabor.domain.Customer;
import org.springframework.data.util.CloseableIterator;

import java.util.Date;

public interface CustomCustomerRepository {
    CloseableIterator<Customer> streamCustomersBornBetween(Date start, Date end);
    public Long averageCustomersBornBetween(Date start, Date end);
}

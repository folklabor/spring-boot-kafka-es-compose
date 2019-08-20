package com.folklabor.customerreport.service.integration;

import com.folklabor.domain.Customer;
import org.springframework.kafka.support.Acknowledgment;

public interface CustomerConsumer {
    void consume(Customer customer, Acknowledgment ack);
}

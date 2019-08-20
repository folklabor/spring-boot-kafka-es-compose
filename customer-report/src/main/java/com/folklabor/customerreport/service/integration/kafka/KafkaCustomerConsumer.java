package com.folklabor.customerreport.service.integration.kafka;

import com.folklabor.customerreport.service.CustomerService;
import com.folklabor.customerreport.service.integration.CustomerConsumer;
import com.folklabor.domain.Customer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class KafkaCustomerConsumer implements CustomerConsumer {

    @Autowired
    private CustomerService customerService;

    /**
     * Kafka listener using ack-mode: manual_immediate so that
     * if there are issues with saving the customer, it will be retried
     * @param customer
     * @param ack
     */
    @Override
    @KafkaListener(topics = "${customer-report.customer-topic}")
    public void consume(Customer customer, Acknowledgment ack) {
        log.info("Kafka consumer Received Customer {}", customer);
        try{
            customerService.save(customer);
            ack.acknowledge();
        } catch(Exception e){
            throw new RuntimeException("Unable to persist Customer. Will not acknowledge");
        }

    }
}

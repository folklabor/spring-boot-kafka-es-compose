package com.folklabor.customerproducer.service.integration.kafka;


import com.folklabor.customerproducer.service.integration.CustomerProducer;
import com.folklabor.domain.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaCustomerProducer implements CustomerProducer {

    @Value("${customer-producer.customer-topic}")
    private String customerTopic;

    @Autowired
    private KafkaTemplate<String, Customer> kafkaTemplate;

    @Override
    public void produce(Customer customer) {
        this.kafkaTemplate.send(customerTopic, customer);
    }
}

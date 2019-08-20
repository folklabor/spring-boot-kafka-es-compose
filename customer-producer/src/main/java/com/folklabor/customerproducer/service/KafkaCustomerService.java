package com.folklabor.customerproducer.service;

import com.folklabor.domain.Customer;
import com.folklabor.customerproducer.service.integration.CustomerProducer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class KafkaCustomerService implements CustomerService {
    @Autowired
    private CustomerProducer customerProducer;

    public void save(Customer customer){
        log.info("Saving customer {}", customer);

        try {
            customerProducer.produce(customer);
            log.info("Customer saved.");
        } catch(Exception e){
            throw new RuntimeException("Unable to save Customer.");
        }
    }
}

package com.folklabor.customerproducer.service;

import com.folklabor.customerproducer.service.integration.CustomerProducer;
import com.folklabor.domain.Customer;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.doThrow;

@RunWith(MockitoJUnitRunner.class)
public class KafkaCustomerServiceTest {

    @Mock
    private CustomerProducer producer;

    @InjectMocks
    private KafkaCustomerService customerService;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test(expected = RuntimeException.class)
    public void testSaveShouldHandleProducerException() {

        // throw an exception any time the producer is called to produce
        doThrow(Exception.class)
                .when(producer)
                .produce(isA(Customer.class));

        customerService.save(new Customer());
    }
}
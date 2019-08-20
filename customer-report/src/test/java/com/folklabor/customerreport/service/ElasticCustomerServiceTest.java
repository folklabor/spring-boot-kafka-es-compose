package com.folklabor.customerreport.service;

import com.folklabor.customerreport.repository.CustomerRepository;
import com.folklabor.domain.Customer;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.util.CloseableIterator;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ElasticCustomerServiceTest {
    @Mock
    private CustomerRepository repository;

    @InjectMocks
    private ElasticCustomerService customerService;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testFindMedianAgeOfCustomersBornBetween() {
        CloseableIterator<Customer> customers = mock(CloseableIterator.class);
        when(customers.hasNext()).thenReturn(true,true, true, false);
        when(customers.next())
                .thenReturn(createCustomerWithBirthdate(4))
                .thenReturn(createCustomerWithBirthdate(3))
                .thenReturn(createCustomerWithBirthdate(1));
        when(repository.streamCustomersBornBetween(any(Date.class), any(Date.class)))
                .thenReturn(customers);

        double medianAge = customerService.findMedianAgeOfCustomersBornBetween(new Date(), new Date());

        assertEquals(3.0, medianAge, 0);
    }

    private Customer createCustomerWithBirthdate(int yearsOld){
        LocalDate now = LocalDate.now();
        Date birthdate = Date.from(now.minusYears(yearsOld).atStartOfDay(ZoneId.systemDefault()).toInstant());

        Customer customer = new Customer();
        customer.setDateOfBirth(birthdate);

        return customer;
    }

    @Test
    public void findAverageAgeOfCustomersBornBetween() {
    }
}
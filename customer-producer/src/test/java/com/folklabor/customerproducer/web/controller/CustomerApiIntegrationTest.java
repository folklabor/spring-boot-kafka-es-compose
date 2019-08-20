package com.folklabor.customerproducer.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.folklabor.customerproducer.service.CustomerService;
import com.folklabor.customerproducer.web.command.CreateCustomer;
import com.folklabor.customerproducer.web.command.UpdateCustomer;
import com.folklabor.domain.Customer;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Date;

import static org.mockito.ArgumentMatchers.isA;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest
public class CustomerApiIntegrationTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CustomerService customerService;

    @Before
    public void setUp() {
        Mockito.doNothing().when(customerService).save(isA(Customer.class));
    }

    @Test
    public void testCreateCustomerWithNoBodyShouldFail() throws Exception {
        ResultMatcher expectedResult = status().isBadRequest();
        RequestBuilder requestBuilder = buildCustomerPostRequestWithBody("");
        mockMvc.perform(requestBuilder).andExpect(expectedResult);
    }

    @Test
    public void testCreateCustomerWithNoFirstNameShouldFail() throws Exception {
        ResultMatcher expectedResult = status().isBadRequest();
        CreateCustomer unNamedCustomer = new CreateCustomer();
        unNamedCustomer.setLastName("Lastname");
        unNamedCustomer.setDateOfBirth(new Date());
        RequestBuilder requestBuilder = buildCustomerPostRequestFor(unNamedCustomer);
        mockMvc.perform(requestBuilder).andExpect(expectedResult);
    }

    @Test
    public void testCreateCustomerShouldSucceed() throws Exception {
        ResultMatcher expectedResult = status().isOk();
        CreateCustomer wellFormedCustomer = new CreateCustomer();
        wellFormedCustomer.setFirstName("Firstname");
        wellFormedCustomer.setLastName("Lastname");
        wellFormedCustomer.setDateOfBirth(new Date());
        RequestBuilder requestBuilder = buildCustomerPostRequestFor(wellFormedCustomer);
        mockMvc.perform(requestBuilder).andExpect(expectedResult);
    }

    @Test
    public void testUpdateCustomerWithNoFirstNameShouldFail() throws Exception {
        ResultMatcher expectedResult = status().isBadRequest();
        UpdateCustomer unNamedCustomer = new UpdateCustomer();
        unNamedCustomer.setId("test111");
        unNamedCustomer.setLastName("Lastname");
        unNamedCustomer.setDateOfBirth(new Date());
        RequestBuilder requestBuilder = buildCustomerPutRequestFor(unNamedCustomer);
        mockMvc.perform(requestBuilder).andExpect(expectedResult);
    }

    @Test
    public void testUpdateCustomerWithNoFirstNameShouldSucceed() throws Exception {
        ResultMatcher expectedResult = status().isOk();
        UpdateCustomer wellFormedCustomer = new UpdateCustomer();
        wellFormedCustomer.setId("test111");
        wellFormedCustomer.setFirstName("Firstname");
        wellFormedCustomer.setLastName("Lastname");
        wellFormedCustomer.setDateOfBirth(new Date());
        RequestBuilder requestBuilder = buildCustomerPutRequestFor(wellFormedCustomer);
        mockMvc.perform(requestBuilder).andExpect(expectedResult);
    }

    private RequestBuilder buildCustomerPostRequestFor(CreateCustomer customer){
        return buildCustomerPostRequestWithBody(toJson(customer));
    }

    private RequestBuilder buildCustomerPutRequestFor(UpdateCustomer customer){
        return buildCustomerPutRequestWithBody(customer.getId(), toJson(customer));
    }

    private RequestBuilder buildCustomerPostRequestWithBody(String body){
        return MockMvcRequestBuilders
                .post("/customer")
                .accept(MediaType.APPLICATION_JSON)
                .content(body)
                .contentType(MediaType.APPLICATION_JSON);
    }

    private RequestBuilder buildCustomerPutRequestWithBody(String customerId, String body){
        return MockMvcRequestBuilders
                .put("/customer/" + customerId)
                .accept(MediaType.APPLICATION_JSON)
                .content(body)
                .contentType(MediaType.APPLICATION_JSON);
    }

    public static String toJson(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

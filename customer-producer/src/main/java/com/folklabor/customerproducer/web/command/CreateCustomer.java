package com.folklabor.customerproducer.web.command;

import com.folklabor.domain.Customer;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.beans.BeanUtils;

@EqualsAndHashCode(callSuper = true)
@Data
public class CreateCustomer extends CustomerOperations{
    public Customer toCustomer(){
        Customer customer = new Customer();
        BeanUtils.copyProperties(this, customer);
        return customer;
    }
}

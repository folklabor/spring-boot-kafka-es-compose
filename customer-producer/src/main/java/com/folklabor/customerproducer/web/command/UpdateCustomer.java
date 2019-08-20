package com.folklabor.customerproducer.web.command;

import com.folklabor.domain.Customer;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.beans.BeanUtils;

import javax.validation.constraints.NotBlank;

@EqualsAndHashCode(callSuper = true)
@Data
public class UpdateCustomer extends CustomerOperations{
    @NotBlank
    private String id;

    public Customer toCustomer(){
        Customer customer = new Customer();
        BeanUtils.copyProperties(this, customer);
        return customer;
    }
}

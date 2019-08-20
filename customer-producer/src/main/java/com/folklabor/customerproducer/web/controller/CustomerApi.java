package com.folklabor.customerproducer.web.controller;

import com.folklabor.customerproducer.service.CustomerService;
import com.folklabor.customerproducer.web.command.CreateCustomer;
import com.folklabor.customerproducer.web.command.UpdateCustomer;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/customer")
@Api("Set of endpoints for Customer management. ")
public class CustomerApi {
    @Autowired
    private CustomerService customerService;

    @PostMapping
    @ApiOperation("Create a new customer.")
    public void create(@RequestBody @Valid CreateCustomer customer){
        customerService.save(customer.toCustomer());
    }

    @PostMapping("/copy")
    @ApiOperation("Create a new customer and copy that customer n times.")
    public void createCopies(
            @ApiParam("Number of times to copy the provided customer.")
            @RequestBody @Valid CreateCustomer customer, @RequestParam int numberOfCopies){
        for(int i=0; i<numberOfCopies; i++){
            customerService.save(customer.toCustomer());
        }
    }

    @PutMapping("/{id}")
    @ApiOperation("Update an existing customer.")
    public void update(@RequestBody @Valid UpdateCustomer customer, @PathVariable String id){
        customer.setId(id);
        customerService.save(customer.toCustomer());
    }
}

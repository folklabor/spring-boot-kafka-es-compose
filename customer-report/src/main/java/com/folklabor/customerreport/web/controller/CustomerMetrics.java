package com.folklabor.customerreport.web.controller;

import com.folklabor.customerreport.service.CustomerService;
import com.folklabor.customerreport.web.command.MedianAgeResponse;
import com.folklabor.customerreport.web.command.AverageAgeResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

@Slf4j
@Controller
@Api("Set of endpoints for Customer Metrics")
@RequestMapping("/customer/metrics")
public class CustomerMetrics {

    @Autowired
    private CustomerService customerService;

    @ResponseBody
    @GetMapping(value="median-age", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation("Get the median age of all Customers in the system born between the provided dates.")
    public MedianAgeResponse getMedianAgeOfCustomersBornBetween(
            @ApiParam("Start date of the search. Provide formatted as 2003-02-13")
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date start,
            @ApiParam("End date of the search. Provide formatted as 2003-02-13")
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date end){
        return MedianAgeResponse.builder()
                .requestBeginDate(start)
                .requestEndDate(end)
                .medianAge(customerService.findMedianAgeOfCustomersBornBetween(start, end))
                .build();
    }

    @ResponseBody
    @GetMapping(value="average-age", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation("Get the average age of all Customers in the system born between the provided dates.")
    public AverageAgeResponse getAverageAgeOfCustomersBornBetween(
            @ApiParam("Start date of the search. Provide formatted as 2003-02-13")
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date start,
            @ApiParam("End date of the search. Provide formatted as 2003-02-13")
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date end){
        return AverageAgeResponse.builder()
                .requestBeginDate(start)
                .requestEndDate(end)
                .averageAge(customerService.findAverageAgeOfCustomersBornBetween(start, end))
                .build();
    }

}

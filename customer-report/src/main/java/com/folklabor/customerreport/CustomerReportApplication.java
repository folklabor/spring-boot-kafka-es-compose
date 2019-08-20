package com.folklabor.customerreport;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

@SpringBootApplication
@EnableElasticsearchRepositories(basePackages = "com.folklabor.customerreport.repository")
public class CustomerReportApplication {
    public static void main(String[] args) {
        SpringApplication.run(CustomerReportApplication.class, args);
    }
}

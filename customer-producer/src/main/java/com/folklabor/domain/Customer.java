package com.folklabor.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Customer {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String id;
    private String firstName;
    private String lastName;
    private Date dateOfBirth;
}

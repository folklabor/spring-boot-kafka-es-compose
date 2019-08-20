package com.folklabor.customerproducer.web.command;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@NoArgsConstructor
@Data
public abstract class CustomerOperations {
    @Size(max=100)
    @NotNull
    private String firstName;

    @Size(max=100)
    @NotNull
    private String lastName;

    @NotNull
    private Date dateOfBirth;
}

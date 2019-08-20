package com.folklabor.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;
import java.util.Optional;
import java.util.function.Function;

@Document(indexName = "customers", type = "customer")
@Data
public class Customer {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Id
    private String id;
    private String firstName;
    private String lastName;

    @Field(type =   FieldType.Date)
    private Date dateOfBirth;

    @JsonIgnore
    public Optional<LocalDate> getDateOfBirthAsLocalDate(){
        Function<Date, LocalDate> dateLocalDate = d -> d.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        return Optional.ofNullable(getDateOfBirth())
                .map(dateLocalDate);
    }

    @JsonIgnore
    public Integer calculateYearsAlive(){
        LocalDate now = LocalDate.now();
        Function<LocalDate, Period> lifeTime = birthday -> Period.between(birthday, now);
        return getDateOfBirthAsLocalDate().map(lifeTime).map(Period::getYears).orElse(0);
    }
}

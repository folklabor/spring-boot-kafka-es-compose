package com.folklabor.customerreport.web.command;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class MedianAgeResponse {
    private Date requestBeginDate;
    private Date requestEndDate;
    private Double medianAge;
}

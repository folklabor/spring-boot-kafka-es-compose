package com.folklabor.customerreport.utils;

import java.util.concurrent.TimeUnit;

public class TimeUtils {
    public static long millisecondsToYears(double milliseconds){
        long days = TimeUnit.MILLISECONDS.toDays((long)milliseconds);
        return days / 365;
    }
}

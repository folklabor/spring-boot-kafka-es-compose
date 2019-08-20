package com.folklabor.customerreport.utils;

import org.apache.commons.math3.stat.descriptive.rank.Median;

import java.util.Collection;

public class MathUtils {
     /***
     * Get Median value of a collection
     * @param collection
     * @return If there is an odd number of numbers, this middle number is the median. If there is an
     * even number of numbers add the two middles and divide by 2
     */
     public static double medianOf(Collection<Double> collection){

         Median median = new Median();
         double[] values = collection.stream().mapToDouble(Double::doubleValue).toArray();

         return median.evaluate(values);
     }
}

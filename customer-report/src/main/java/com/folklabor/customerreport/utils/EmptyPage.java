package com.folklabor.customerreport.utils;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

/***
 * Necessary to produce an aggregation only (no documents)
 * Elasticsearch query with NativeQueryBuilder. From answer:
 *
 * https://www.oipapio.com/question-6975525
 */
public class EmptyPage implements Pageable {
    public static final EmptyPage INSTANCE = new EmptyPage();

    @Override
    public int getPageNumber() {
        return 0;
    }

    @Override
    public int getPageSize() {
        return 0;
    }

    @Override
    public long getOffset() {
        return 0;
    }

    @Override
    public Sort getSort() {
        return null;
    }

    @Override
    public Pageable next() {
        return null;
    }

    @Override
    public Pageable previousOrFirst() {
        return null;
    }

    @Override
    public Pageable first() {
        return null;
    }

    @Override
    public boolean hasPrevious() {
        return false;
    }
}

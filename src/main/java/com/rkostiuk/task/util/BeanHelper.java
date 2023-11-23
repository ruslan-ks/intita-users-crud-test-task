package com.rkostiuk.task.util;

import com.rkostiuk.task.exception.BeanHelperException;

public interface BeanHelper {
    void copyNonNullProperties(Object destination, Object source) throws BeanHelperException;
}

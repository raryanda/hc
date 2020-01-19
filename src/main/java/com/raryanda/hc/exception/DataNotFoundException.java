package com.raryanda.hc.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class DataNotFoundException extends RuntimeException {

    public DataNotFoundException(String dataName, String fieldName, Object fieldValue) {
        super(String.format("%s not found with %s = %s", dataName, fieldName, fieldValue));
    }
}

package com.benz.hib.jpa.api.exception;

public class DataNotFoundException extends RuntimeException {

    public DataNotFoundException(String msg)
    {
        super(msg);
    }
}

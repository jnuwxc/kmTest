package com.example.kmtest.util;

public class BussinessException extends RuntimeException {
    public BussinessException(Object Obj) {
        super(Obj.toString());
    }
}

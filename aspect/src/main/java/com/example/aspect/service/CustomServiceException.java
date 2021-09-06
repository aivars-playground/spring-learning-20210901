package com.example.aspect.service;

public class CustomServiceException extends RuntimeException {
    public CustomServiceException(String s) {
        super(s);
    }
}

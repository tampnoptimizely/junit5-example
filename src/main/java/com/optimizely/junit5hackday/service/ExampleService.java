package com.optimizely.junit5hackday.service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class ExampleService {
    public String showHttpStatus(Integer statusCode) {
        if (Objects.isNull(statusCode)) {
            throw new NullPointerException();
        }
        HttpStatus httpStatus = HttpStatus.valueOf(statusCode);
        return httpStatus.name();
    }
}

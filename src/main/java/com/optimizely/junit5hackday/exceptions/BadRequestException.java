package com.optimizely.junit5hackday.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;

public class BadRequestException extends HttpClientErrorException {
    public BadRequestException() {
        super(HttpStatus.BAD_REQUEST);
    }
}

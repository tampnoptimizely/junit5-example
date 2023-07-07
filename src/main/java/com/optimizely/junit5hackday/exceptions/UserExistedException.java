package com.optimizely.junit5hackday.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;

public class UserExistedException extends HttpClientErrorException {
    public UserExistedException() {
        super(HttpStatus.CONFLICT);
    }
}

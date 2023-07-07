package com.optimizely.junit5hackday.payloads;

import lombok.Data;

@Data
public class RequestToken {
    private String username;
    private String password;
}

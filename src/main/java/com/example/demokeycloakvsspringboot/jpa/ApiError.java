package com.example.demokeycloakvsspringboot.jpa;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApiError {
    private int status;
    private String message;
    private String source;

    public ApiError(int status, String message, String source) {
        this.status = status;
        this.message = message;
        this.source = source;
    }
}

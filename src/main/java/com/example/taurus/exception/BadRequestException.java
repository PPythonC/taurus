package com.example.taurus.exception;

import org.springframework.http.HttpStatus;

public class BadRequestException extends TaurusException {
    public BadRequestException(String message) {
        super(message);
    }
    public BadRequestException(String message,Throwable cause)
    {
        super(message, cause);
    }

    @Override
    public HttpStatus getStatus() {
        return null;
    }
}

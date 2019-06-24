package com.example.taurus.exception;

import org.springframework.http.HttpStatus;

public class AlreadExistsException extends TaurusException {
    public AlreadExistsException(String message) {
        super(message);
    }

    public AlreadExistsException(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public HttpStatus getStatus() {
        return null;
    }
}

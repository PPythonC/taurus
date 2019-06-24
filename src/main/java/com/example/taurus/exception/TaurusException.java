package com.example.taurus.exception;

import org.springframework.http.HttpStatus;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

public abstract class TaurusException extends RuntimeException {
    /**
     * Error errorData.
     */
    private Object errorData;

    public TaurusException(String message) {
        super(message);
    }

    public TaurusException(String message, Throwable cause) {
        super(message, cause);
    }

    @NonNull
    public abstract HttpStatus getStatus();

    @Nullable
    public Object getErrorData() {
        return errorData;
    }

    /**
     * Sets error errorData.
     *
     * @param errorData error data
     * @return current exception.
     */
    @NonNull
    public TaurusException setErrorData(@Nullable Object errorData) {
        this.errorData = errorData;
        return this;
    }
}

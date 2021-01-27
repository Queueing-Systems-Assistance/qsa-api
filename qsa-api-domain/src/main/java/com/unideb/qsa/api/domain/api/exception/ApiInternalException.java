package com.unideb.qsa.api.domain.api.exception;

/**
 * Exception for internal errors.
 */
public class ApiInternalException extends RuntimeException {

    public ApiInternalException() {
    }

    public ApiInternalException(String message) {
        super(message);
    }

    public ApiInternalException(String message, Throwable cause) {
        super(message, cause);
    }

    public ApiInternalException(Throwable cause) {
        super(cause);
    }

    public ApiInternalException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

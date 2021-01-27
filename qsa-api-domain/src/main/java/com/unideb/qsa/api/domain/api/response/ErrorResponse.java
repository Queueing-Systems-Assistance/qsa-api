package com.unideb.qsa.api.domain.api.response;

import java.util.Map;

/**
 * Domain class for readable error messages for the user.
 */
public class ErrorResponse {

    private String message;
    private Map<String, Object> extensions;

    public Map<String, Object> getExtensions() {
        return extensions;
    }

    public void setExtensions(final Map<String, Object> extensions) {
        this.extensions = extensions;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(final String message) {
        this.message = message;
    }
}

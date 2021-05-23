package com.unideb.qsa.api.domain.api.exception;

import java.util.List;
import java.util.Map;

import graphql.ErrorType;
import graphql.language.SourceLocation;

/**
 * Exception for internal errors.
 */
public class GraphQLInternalException extends GraphQLException {

    private static final String MESSAGE = "Request ID: %s";
    private static final Map<String, Object> NO_EXTENSIONS = Map.of();
    private static final List<SourceLocation> NO_LOCATIONS = null;
    private final String requestId;

    public GraphQLInternalException(String requestId) {
        this.requestId = requestId;
    }

    @Override
    public String getMessage() {
        return String.format(MESSAGE, requestId);
    }

    @Override
    public List<SourceLocation> getLocations() {
        return NO_LOCATIONS;
    }

    @Override
    public ErrorType getErrorType() {
        return ErrorType.ExecutionAborted;
    }

    @Override
    public Map<String, Object> getExtensions() {
        return NO_EXTENSIONS;
    }
}

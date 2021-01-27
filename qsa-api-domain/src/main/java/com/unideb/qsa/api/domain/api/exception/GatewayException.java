package com.unideb.qsa.api.domain.api.exception;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import graphql.ErrorType;
import graphql.GraphQLError;
import graphql.language.SourceLocation;

import com.unideb.qsa.api.domain.api.response.ErrorResponse;

/**
 * Exceptions for internal server errors.
 */
public class GatewayException extends ApiInternalException implements GraphQLError {

    private static final String NO_MESSAGE = null;
    private static final Map<String, Object> NO_EXTENSIONS = Map.of();
    private static final List<SourceLocation> NO_LOCATIONS = null;
    private static final ErrorResponse NO_ERROR_RESPONSE = null;

    private final ErrorResponse errorResponse;

    public GatewayException() {
        errorResponse = NO_ERROR_RESPONSE;
    }

    public GatewayException(ErrorResponse errorResponse) {
        this.errorResponse = errorResponse;
    }

    @Override
    public String getMessage() {
        return Optional.ofNullable(errorResponse)
                       .map(ErrorResponse::getMessage)
                       .orElse(NO_MESSAGE);
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
        return Optional.ofNullable(errorResponse)
                       .map(ErrorResponse::getExtensions)
                       .orElse(NO_EXTENSIONS);
    }
}

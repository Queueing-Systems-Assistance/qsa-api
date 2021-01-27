package com.unideb.qsa.api.domain.api.exception;

import graphql.ErrorType;

import com.unideb.qsa.api.domain.api.response.ErrorResponse;

/**
 * Exception for error messages.
 */
public class ErrorMessageException extends GatewayException {

    public ErrorMessageException(ErrorResponse errorResponse) {
        super(errorResponse);
    }

    @Override
    public ErrorType getErrorType() {
        return ErrorType.ValidationError;
    }
}

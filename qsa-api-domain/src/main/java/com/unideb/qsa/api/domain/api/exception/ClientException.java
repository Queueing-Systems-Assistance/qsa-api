package com.unideb.qsa.api.domain.api.exception;

import graphql.ErrorType;

import com.unideb.qsa.api.domain.api.response.ErrorResponse;

/**
 * Exception for client exceptions.
 */
public class ClientException extends GatewayException {

    public ClientException() {
    }

    public ClientException(ErrorResponse errorResponse) {
        super(errorResponse);
    }

    @Override
    public ErrorType getErrorType() {
        return ErrorType.OperationNotSupported;
    }
}

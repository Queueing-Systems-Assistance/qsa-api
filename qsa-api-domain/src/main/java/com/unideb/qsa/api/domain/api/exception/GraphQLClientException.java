package com.unideb.qsa.api.domain.api.exception;

import graphql.ErrorType;

import com.unideb.qsa.api.domain.api.response.ErrorResponse;

/**
 * Exception for client exceptions.
 */
public class GraphQLClientException extends GraphQLException {

    public GraphQLClientException(ErrorResponse errorResponse) {
        super(errorResponse);
    }

    @Override
    public ErrorType getErrorType() {
        return ErrorType.OperationNotSupported;
    }
}

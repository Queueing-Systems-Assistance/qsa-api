package com.unideb.qsa.api.server.handler;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import graphql.ExceptionWhileDataFetching;
import graphql.GraphQLError;
import graphql.servlet.GraphQLErrorHandler;

import com.unideb.qsa.api.domain.api.exception.GraphQLErrorAdaptorException;

/**
 * Error handler for {@link GraphQLError}s. It also hides internal server errors, so that the stacktrace is not visible for the user.
 */
@Component
public class ExceptionHandler implements GraphQLErrorHandler {

    @Override
    public List<GraphQLError> processErrors(List<GraphQLError> list) {
        return list.stream().map(this::getNested).map(GraphQLErrorAdaptorException::new).collect(Collectors.toList());
    }

    private GraphQLError getNested(GraphQLError error) {
        GraphQLError result = error;
        if (error instanceof ExceptionWhileDataFetching) {
            ExceptionWhileDataFetching exceptionError = (ExceptionWhileDataFetching) error;
            if (exceptionError.getException() instanceof GraphQLError) {
                result = (GraphQLError) exceptionError.getException();
            }
        }
        return result;
    }
}

package com.unideb.qsa.api.server.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;

import graphql.GraphQLError;
import graphql.execution.DataFetcherExceptionHandler;
import graphql.execution.DataFetcherExceptionHandlerParameters;
import graphql.execution.DataFetcherExceptionHandlerResult;

import com.unideb.qsa.api.domain.api.exception.GraphQLException;
import com.unideb.qsa.api.domain.api.exception.GraphQLInternalException;

/**
 * Error handler for {@link GraphQLError}s. It also hides internal server errors, so that the stacktrace is not visible for the user.
 */
@Component
public class ExceptionHandler implements DataFetcherExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionHandler.class);

    @Override
    public DataFetcherExceptionHandlerResult onException(DataFetcherExceptionHandlerParameters handlerParameters) {
        DataFetcherExceptionHandlerResult result;
        Throwable thrownException = handlerParameters.getException();
        if (thrownException instanceof GraphQLException) {
            GraphQLError exception = (GraphQLException) thrownException;
            result = createErrorResult(exception);
            LOGGER.warn("Checked exception occurred [{}]", thrownException.getMessage());
        } else {
            String requestId = MDC.get("requestId");
            result = createErrorResult(new GraphQLInternalException(requestId));
            LOGGER.error("Unchecked exception occurred", thrownException);
        }
        return result;
    }

    private DataFetcherExceptionHandlerResult createErrorResult(GraphQLError exception) {
        return DataFetcherExceptionHandlerResult.newResult()
                                                .error(exception)
                                                .build();
    }
}

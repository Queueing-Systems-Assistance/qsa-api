package com.unideb.qsa.api.domain.api.exception;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import graphql.ErrorType;
import graphql.GraphQLError;
import graphql.language.SourceLocation;

/**
 * Exception holder, which hides the stacktrace from the output.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GraphQLErrorAdaptorException implements GraphQLError {

    private final GraphQLError graphQLError;

    public GraphQLErrorAdaptorException(GraphQLError graphQLError) {
        this.graphQLError = graphQLError;
    }

    @Override
    public List<SourceLocation> getLocations() {
        return null;
    }

    @Override
    public ErrorType getErrorType() {
        return graphQLError.getErrorType();
    }

    @Override
    public String getMessage() {
        return graphQLError.getMessage();
    }

    @Override
    public Map<String, Object> getExtensions() {
        return graphQLError.getExtensions();
    }

    @Override
    public List<Object> getPath() {
        return graphQLError.getPath();
    }
}

package com.unideb.qsa.api.client.config.gateway;


import java.util.Map;
import java.util.function.Function;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;

import com.unideb.qsa.api.client.config.request.QsaApiRequestComponentResolver;
import com.unideb.qsa.api.domain.api.exception.ApiInternalException;
import com.unideb.qsa.api.domain.api.response.ErrorResponse;


/**
 * Configuration holder class for a {@link Gateway} implementation.
 * @param <RESPONSE> Type of the response body.
 * @param <E>        Type of the thrown wrapper exception.
 */
public final class GatewayConfiguration<RESPONSE, E extends ApiInternalException> {

    private final String targetApp;
    private final QsaApiRequestComponentResolver requestComponentsMessageResolver;
    private final Map<HttpStatus, Function<ErrorResponse, E>> exceptionProvider;
    private final ParameterizedTypeReference<RESPONSE> responseType;
    private final HttpMethod httpMethod;

    private GatewayConfiguration(Builder<RESPONSE, E> builder) {
        this.targetApp = builder.targetApp;
        this.requestComponentsMessageResolver = builder.requestComponentsMessageResolver;
        this.exceptionProvider = builder.exceptionProvider;
        this.httpMethod = builder.httpMethod;
        this.responseType = builder.responseType;
    }

    public ParameterizedTypeReference<RESPONSE> getResponseType() {
        return responseType;
    }

    public String getTargetApp() {
        return targetApp;
    }

    public QsaApiRequestComponentResolver getRequestComponentsMessageResolver() {
        return requestComponentsMessageResolver;
    }

    public Function<ErrorResponse, E> getExceptionProvider(HttpStatus httpStatus) {
        return exceptionProvider.get(httpStatus);
    }

    public HttpMethod getHttpMethod() {
        return httpMethod;
    }

    /**
     * Builder class for {@link GatewayConfiguration}.
     * @param <RESPONSE> Type of the response body.
     * @param <E>        Type of the thrown wrapper exception.
     */
    public static final class Builder<RESPONSE, E extends ApiInternalException> {

        private Map<HttpStatus, Function<ErrorResponse, E>> exceptionProvider;
        private QsaApiRequestComponentResolver requestComponentsMessageResolver;
        private String targetApp;
        private ParameterizedTypeReference<RESPONSE> responseType;
        private HttpMethod httpMethod;

        public Builder() {
        }

        public Builder<RESPONSE, E> withTargetApp(String targetApp) {
            this.targetApp = targetApp;
            return this;
        }

        public Builder<RESPONSE, E> withExceptionProvider(Map<HttpStatus, Function<ErrorResponse, E>> exceptionProvider) {
            this.exceptionProvider = exceptionProvider;
            return this;
        }

        public Builder<RESPONSE, E> withHttpMethod(HttpMethod httpMethod) {
            this.httpMethod = httpMethod;
            return this;
        }

        public Builder<RESPONSE, E> withResponseType(ParameterizedTypeReference<RESPONSE> responseType) {
            this.responseType = responseType;
            return this;
        }

        public Builder<RESPONSE, E> withRequestComponentsMessageResolver(QsaApiRequestComponentResolver requestComponentsMessageResolver) {
            this.requestComponentsMessageResolver = requestComponentsMessageResolver;
            return this;
        }

        public GatewayConfiguration<RESPONSE, E> build() {
            return new GatewayConfiguration<>(this);
        }
    }
}

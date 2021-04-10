package com.unideb.qsa.api.client.i18nservice;

import java.util.List;
import java.util.Map;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.web.reactive.function.client.WebClient;

import com.unideb.qsa.api.client.config.gateway.Gateway;
import com.unideb.qsa.api.client.config.gateway.GatewayConfiguration;
import com.unideb.qsa.api.client.config.gateway.WebClientGateway;
import com.unideb.qsa.api.client.config.request.DefaultQsaApiRequestComponentResolver;
import com.unideb.qsa.api.client.config.request.QsaApiRequestComponent;
import com.unideb.qsa.api.domain.api.exception.GraphQLClientException;
import com.unideb.qsa.api.domain.api.exception.GraphQLException;
import com.unideb.qsa.api.domain.api.response.ErrorResponse;
import com.unideb.qsa.api.domain.i18nservice.response.I18nElementResponse;

/**
 * Configuration for i18n-service gateway.
 */
@Configuration
public class I18nServiceGatewayConfig {


    private static final String TARGET_APP_DISPLAY_NAME = "I18n Service";
    private static final Map<HttpStatus, Function<ErrorResponse, GraphQLException>> EXCEPTION_PROVIDER = Map.of(
            HttpStatus.NOT_FOUND, GraphQLClientException::new,
            HttpStatus.UNPROCESSABLE_ENTITY, GraphQLClientException::new,
            HttpStatus.INTERNAL_SERVER_ERROR, GraphQLException::new);
    private static final DefaultQsaApiRequestComponentResolver REQUEST_COMPONENT_RESOLVER = new DefaultQsaApiRequestComponentResolver(List.of(
            QsaApiRequestComponent.URL,
            QsaApiRequestComponent.BODY));

    @Autowired
    private WebClient webClient;

    @Bean
    public Gateway<List<I18nElementResponse>> i18nServiceKeysGateway() {
        return new WebClientGateway<>(webClient, i18nServiceKeysGatewayConfig());
    }

    private GatewayConfiguration<List<I18nElementResponse>, GraphQLException> i18nServiceKeysGatewayConfig() {
        return new GatewayConfiguration.Builder<List<I18nElementResponse>, GraphQLException>()
                .withTargetApp(TARGET_APP_DISPLAY_NAME)
                .withExceptionProvider(EXCEPTION_PROVIDER)
                .withRequestComponentsMessageResolver(REQUEST_COMPONENT_RESOLVER)
                .withResponseType(new ParameterizedTypeReference<List<I18nElementResponse>>() {})
                .withHttpMethod(HttpMethod.POST)
                .build();
    }

}

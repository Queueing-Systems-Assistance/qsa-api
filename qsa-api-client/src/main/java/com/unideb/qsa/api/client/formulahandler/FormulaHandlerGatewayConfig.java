package com.unideb.qsa.api.client.formulahandler;

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
import com.unideb.qsa.api.domain.api.exception.GraphQLErrorMessageException;
import com.unideb.qsa.api.domain.api.exception.GraphQLException;
import com.unideb.qsa.api.domain.api.response.ErrorResponse;

/**
 * Configuration for formula-handler gateway.
 */
@Configuration
public class FormulaHandlerGatewayConfig {

    private static final String TARGET_APP_DISPLAY_NAME = "Formula Handler";
    private static final Map<HttpStatus, Function<ErrorResponse, GraphQLException>> EXCEPTION_PROVIDER = Map.of(
            HttpStatus.BAD_REQUEST, GraphQLErrorMessageException::new,
            HttpStatus.NOT_FOUND, GraphQLClientException::new,
            HttpStatus.INTERNAL_SERVER_ERROR, GraphQLException::new);
    private static final DefaultQsaApiRequestComponentResolver REQUEST_COMPONENT_RESOLVER = new DefaultQsaApiRequestComponentResolver(List.of(
            QsaApiRequestComponent.URL,
            QsaApiRequestComponent.HEADERS,
            QsaApiRequestComponent.BODY));

    @Autowired
    private WebClient webClient;

    @Bean
    public Gateway<String> formulaDefaultFormulaHandlerGateway() {
        return new WebClientGateway<>(webClient, formulaDefaultGatewayConfig());
    }

    @Bean
    public Gateway<String> formulaStepsFormulaHandlerGateway() {
        return new WebClientGateway<>(webClient, formulaStepsGatewayConfig());
    }

    @Bean
    public Gateway<String> formulaCalculatedFormulaHandlerGateway() {
        return new WebClientGateway<>(webClient, formulaCalculatedGatewayConfig());
    }

    private GatewayConfiguration<String, GraphQLException> formulaDefaultGatewayConfig() {
        return new GatewayConfiguration.Builder<String, GraphQLException>()
                .withTargetApp(TARGET_APP_DISPLAY_NAME)
                .withExceptionProvider(EXCEPTION_PROVIDER)
                .withRequestComponentsMessageResolver(REQUEST_COMPONENT_RESOLVER)
                .withResponseType(new ParameterizedTypeReference<String>() {})
                .withHttpMethod(HttpMethod.POST)
                .build();
    }

    private GatewayConfiguration<String, GraphQLException> formulaStepsGatewayConfig() {
        return new GatewayConfiguration.Builder<String, GraphQLException>()
                .withTargetApp(TARGET_APP_DISPLAY_NAME)
                .withExceptionProvider(EXCEPTION_PROVIDER)
                .withRequestComponentsMessageResolver(REQUEST_COMPONENT_RESOLVER)
                .withResponseType(new ParameterizedTypeReference<String>() {})
                .withHttpMethod(HttpMethod.POST)
                .build();
    }

    private GatewayConfiguration<String, GraphQLException> formulaCalculatedGatewayConfig() {
        return new GatewayConfiguration.Builder<String, GraphQLException>()
                .withTargetApp(TARGET_APP_DISPLAY_NAME)
                .withExceptionProvider(EXCEPTION_PROVIDER)
                .withRequestComponentsMessageResolver(REQUEST_COMPONENT_RESOLVER)
                .withResponseType(new ParameterizedTypeReference<String>() {})
                .withHttpMethod(HttpMethod.POST)
                .build();
    }
}

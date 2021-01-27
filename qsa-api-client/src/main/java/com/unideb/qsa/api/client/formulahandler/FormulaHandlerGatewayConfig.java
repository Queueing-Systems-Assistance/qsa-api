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
import com.unideb.qsa.api.domain.api.exception.ClientException;
import com.unideb.qsa.api.domain.api.exception.ErrorMessageException;
import com.unideb.qsa.api.domain.api.exception.GatewayException;
import com.unideb.qsa.api.domain.api.response.ErrorResponse;

/**
 * Configuration for formula-handler gateway.
 */
@Configuration
public class FormulaHandlerGatewayConfig {

    private static final String TARGET_APP_DISPLAY_NAME = "Formula Handler";
    private static final Map<HttpStatus, Function<ErrorResponse, GatewayException>> EXCEPTION_PROVIDER = Map.of(
            HttpStatus.BAD_REQUEST, ErrorMessageException::new,
            HttpStatus.NOT_FOUND, ClientException::new,
            HttpStatus.INTERNAL_SERVER_ERROR, GatewayException::new);
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
    public Gateway<List<String>> formulaStepsFormulaHandlerGateway() {
        return new WebClientGateway<>(webClient, formulaStepsGatewayConfig());
    }

    @Bean
    public Gateway<List<String>> formulaCalculatedFormulaHandlerGateway() {
        return new WebClientGateway<>(webClient, formulaCalculatedGatewayConfig());
    }

    private GatewayConfiguration<String, GatewayException> formulaDefaultGatewayConfig() {
        return new GatewayConfiguration.Builder<String, GatewayException>()
                .withTargetApp(TARGET_APP_DISPLAY_NAME)
                .withExceptionProvider(EXCEPTION_PROVIDER)
                .withRequestComponentsMessageResolver(REQUEST_COMPONENT_RESOLVER)
                .withResponseType(new ParameterizedTypeReference<String>() {})
                .withHttpMethod(HttpMethod.POST)
                .build();
    }

    private GatewayConfiguration<List<String>, GatewayException> formulaStepsGatewayConfig() {
        return new GatewayConfiguration.Builder<List<String>, GatewayException>()
                .withTargetApp(TARGET_APP_DISPLAY_NAME)
                .withExceptionProvider(EXCEPTION_PROVIDER)
                .withRequestComponentsMessageResolver(REQUEST_COMPONENT_RESOLVER)
                .withResponseType(new ParameterizedTypeReference<List<String>>() {})
                .withHttpMethod(HttpMethod.POST)
                .build();
    }

    private GatewayConfiguration<List<String>, GatewayException> formulaCalculatedGatewayConfig() {
        return new GatewayConfiguration.Builder<List<String>, GatewayException>()
                .withTargetApp(TARGET_APP_DISPLAY_NAME)
                .withExceptionProvider(EXCEPTION_PROVIDER)
                .withRequestComponentsMessageResolver(REQUEST_COMPONENT_RESOLVER)
                .withResponseType(new ParameterizedTypeReference<List<String>>() {})
                .withHttpMethod(HttpMethod.POST)
                .build();
    }
}

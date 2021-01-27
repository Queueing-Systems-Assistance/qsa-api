package com.unideb.qsa.api.client.calculator;

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
import com.unideb.qsa.api.domain.api.response.InputFeature;
import com.unideb.qsa.api.domain.api.response.OutputFeature;
import com.unideb.qsa.api.domain.api.response.StreamOutputFeature;
import com.unideb.qsa.api.domain.api.response.SystemElement;

/**
 * Configuration for calculator gateway.
 */
@Configuration
public class CalculatorGatewayConfig {

    private static final String TARGET_APP_DISPLAY_NAME = "Calculator";
    private static final Map<HttpStatus, Function<ErrorResponse, GatewayException>> EXCEPTION_PROVIDER = Map.of(
            HttpStatus.BAD_REQUEST, ErrorMessageException::new,
            HttpStatus.NOT_FOUND, ClientException::new,
            HttpStatus.METHOD_NOT_ALLOWED, ClientException::new,
            HttpStatus.NOT_ACCEPTABLE, ClientException::new,
            HttpStatus.INTERNAL_SERVER_ERROR, GatewayException::new);
    private static final DefaultQsaApiRequestComponentResolver REQUEST_COMPONENT_RESOLVER = new DefaultQsaApiRequestComponentResolver(List.of(
            QsaApiRequestComponent.URL,
            QsaApiRequestComponent.HEADERS,
            QsaApiRequestComponent.BODY));

    @Autowired
    private WebClient webClient;

    @Bean
    public Gateway<List<OutputFeature>> outputFeatureCalculatorGateway() {
        return new WebClientGateway<>(webClient, outputFeatureGatewayConfig());
    }

    @Bean
    public Gateway<List<SystemElement>> systemElementCalculatorGateway() {
        return new WebClientGateway<>(webClient, systemElementGatewayConfig());
    }

    @Bean
    public Gateway<List<InputFeature>> inputFeatureCalculatorGateway() {
        return new WebClientGateway<>(webClient, inputFeatureGatewayConfig());
    }

    @Bean
    public Gateway<StreamOutputFeature> streamOutputFeatureResponseGateway() {
        return new WebClientGateway<>(webClient, streamOutputFeatureGatewayConfig());
    }

    private GatewayConfiguration<List<OutputFeature>, GatewayException> outputFeatureGatewayConfig() {
        return new GatewayConfiguration.Builder<List<OutputFeature>, GatewayException>()
                .withTargetApp(TARGET_APP_DISPLAY_NAME)
                .withExceptionProvider(EXCEPTION_PROVIDER)
                .withRequestComponentsMessageResolver(REQUEST_COMPONENT_RESOLVER)
                .withResponseType(new ParameterizedTypeReference<List<OutputFeature>>() {})
                .withHttpMethod(HttpMethod.POST)
                .build();
    }

    private GatewayConfiguration<StreamOutputFeature, GatewayException> streamOutputFeatureGatewayConfig() {
        return new GatewayConfiguration.Builder<StreamOutputFeature, GatewayException>()
                .withTargetApp(TARGET_APP_DISPLAY_NAME)
                .withExceptionProvider(EXCEPTION_PROVIDER)
                .withRequestComponentsMessageResolver(REQUEST_COMPONENT_RESOLVER)
                .withResponseType(new ParameterizedTypeReference<StreamOutputFeature>() {})
                .withHttpMethod(HttpMethod.POST)
                .build();
    }

    private GatewayConfiguration<List<SystemElement>, GatewayException> systemElementGatewayConfig() {
        return new GatewayConfiguration.Builder<List<SystemElement>, GatewayException>()
                .withTargetApp(TARGET_APP_DISPLAY_NAME)
                .withExceptionProvider(EXCEPTION_PROVIDER)
                .withRequestComponentsMessageResolver(REQUEST_COMPONENT_RESOLVER)
                .withResponseType(new ParameterizedTypeReference<List<SystemElement>>() {})
                .withHttpMethod(HttpMethod.POST)
                .build();
    }

    private GatewayConfiguration<List<InputFeature>, GatewayException> inputFeatureGatewayConfig() {
        return new GatewayConfiguration.Builder<List<InputFeature>, GatewayException>()
                .withTargetApp(TARGET_APP_DISPLAY_NAME)
                .withExceptionProvider(EXCEPTION_PROVIDER)
                .withRequestComponentsMessageResolver(REQUEST_COMPONENT_RESOLVER)
                .withResponseType(new ParameterizedTypeReference<List<InputFeature>>() {})
                .withHttpMethod(HttpMethod.GET)
                .build();
    }
}

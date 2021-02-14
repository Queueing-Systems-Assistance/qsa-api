package com.unideb.qsa.api.client.calculator;

import java.net.URI;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.unideb.qsa.api.client.config.UriCreator;

/**
 * Configuration for calculator {@link URI}s.
 */
@Configuration
public class CalculatorUriCreatorConfig {

    @Value("${client.calculator.scheme}")
    private String scheme;
    @Value("${client.calculator.port}")
    private String port;
    @Value("${client.calculator.host}")
    private String host;
    @Value("${client.calculator.endpoint.output-feature}")
    private String endpointOutputFeature;
    @Value("${client.calculator.endpoint.system-element}")
    private String endpointSystemElement;
    @Value("${client.calculator.endpoint.output-feature-stream}")
    private String endpointStreamOutputFeature;
    @Value("${client.calculator.endpoint.available-output-feature}")
    private String endpointAvailableOutputFeature;

    @Bean
    public UriCreator calculatorOutputFeatureUriCreator() {
        return new UriCreator(scheme, host, port, endpointOutputFeature);
    }

    @Bean
    public UriCreator calculatorSystemElementUriCreator() {
        return new UriCreator(scheme, host, port, endpointSystemElement);
    }

    @Bean
    public UriCreator calculatorStreamOutputFeatureUriCreator() {
        return new UriCreator(scheme, host, port, endpointStreamOutputFeature);
    }

    @Bean
    public UriCreator calculatorAvailableOutputFeatureUriCreator() {
        return new UriCreator(scheme, host, port, endpointAvailableOutputFeature);
    }
}

package com.unideb.qsa.api.client.calculator;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.unideb.qsa.api.client.calculator.output.CalculatorOutputFeatureClient;
import com.unideb.qsa.api.client.calculator.output.DefaultCalculatorOutputFeatureClient;
import com.unideb.qsa.api.client.calculator.output.stream.CalculatorStreamOutputFeatureClient;
import com.unideb.qsa.api.client.calculator.output.stream.DefaultCalculatorStreamOutputClient;
import com.unideb.qsa.api.client.calculator.system.CalculatorSystemElementClient;
import com.unideb.qsa.api.client.calculator.system.DefaultCalculatorSystemElementClient;
import com.unideb.qsa.api.client.config.UriCreator;
import com.unideb.qsa.api.client.config.client.GenericClient;
import com.unideb.qsa.api.client.config.gateway.Gateway;
import com.unideb.qsa.api.client.config.header.factory.HeaderFactory;
import com.unideb.qsa.api.domain.api.response.OutputFeature;
import com.unideb.qsa.api.domain.api.response.StreamOutputFeature;
import com.unideb.qsa.api.domain.api.response.SystemElement;

/**
 * Configuration class for calculator client.
 */
@Configuration
public class CalculatorClientConfig {

    @Autowired
    private Gateway<List<OutputFeature>> outputFeatureCalculatorGateway;
    @Autowired
    private Gateway<List<SystemElement>> systemElementCalculatorGateway;
    @Autowired
    private Gateway<StreamOutputFeature> streamOutputFeatureResponseGateway;
    @Autowired
    private UriCreator calculatorOutputFeatureUriCreator;
    @Autowired
    private UriCreator calculatorStreamOutputFeatureUriCreator;
    @Autowired
    private UriCreator calculatorSystemElementUriCreator;
    @Autowired
    private HeaderFactory compositeRequestHeadersFactory;

    @Bean
    public CalculatorOutputFeatureClient calculatorOutputFeatureClient() {
        return new DefaultCalculatorOutputFeatureClient(new GenericClient<>(outputFeatureCalculatorGateway, compositeRequestHeadersFactory), calculatorOutputFeatureUriCreator);
    }

    @Bean
    public CalculatorSystemElementClient calculatorSystemElementClient() {
        return new DefaultCalculatorSystemElementClient(new GenericClient<>(systemElementCalculatorGateway, compositeRequestHeadersFactory), calculatorSystemElementUriCreator);
    }

    @Bean
    public CalculatorStreamOutputFeatureClient calculatorStreamOutputFeatureClient() {
        return new DefaultCalculatorStreamOutputClient(new GenericClient<>(streamOutputFeatureResponseGateway, compositeRequestHeadersFactory),
                calculatorStreamOutputFeatureUriCreator);
    }

}

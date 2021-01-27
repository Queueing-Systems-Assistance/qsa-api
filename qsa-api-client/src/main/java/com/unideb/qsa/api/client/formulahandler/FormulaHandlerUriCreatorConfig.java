package com.unideb.qsa.api.client.formulahandler;

import java.net.URI;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.unideb.qsa.api.client.config.UriCreator;

/**
 * Configuration for formula-handler {@link URI}s.
 */
@Configuration
public class FormulaHandlerUriCreatorConfig {


    @Value("${client.formula-handler.scheme}")
    private String scheme;
    @Value("${client.formula-handler.port}")
    private String port;
    @Value("${client.formula-handler.host}")
    private String host;
    @Value("${client.formula-handler.endpoint.formula-default}")
    private String endpointFormulaDefault;
    @Value("${client.formula-handler.endpoint.formula-steps}")
    private String endpointFormulaSteps;
    @Value("${client.formula-handler.endpoint.formula-calculated}")
    private String endpointFormulaCalculated;

    @Bean
    public UriCreator formulaHandlerFormulaDefaultUriCreator() {
        return new UriCreator(scheme, host, port, endpointFormulaDefault);
    }

    @Bean
    public UriCreator formulaHandlerFormulaStepsUriCreator() {
        return new UriCreator(scheme, host, port, endpointFormulaSteps);
    }

    @Bean
    public UriCreator formulaHandlerFormulaCalculatedUriCreator() {
        return new UriCreator(scheme, host, port, endpointFormulaCalculated);
    }

}

package com.unideb.qsa.api.client.formulahandler;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.unideb.qsa.api.client.config.UriCreator;
import com.unideb.qsa.api.client.config.client.GenericClient;
import com.unideb.qsa.api.client.config.gateway.Gateway;
import com.unideb.qsa.api.client.config.header.factory.HeaderFactory;
import com.unideb.qsa.api.client.formulahandler.calculated.DefaultFormulaHandlerFormulaCalculatedClient;
import com.unideb.qsa.api.client.formulahandler.calculated.FormulaHandlerFormulaCalculatedClient;
import com.unideb.qsa.api.client.formulahandler.defaultformula.DefaultFormulaHandlerFormulaDefaultClient;
import com.unideb.qsa.api.client.formulahandler.defaultformula.FormulaHandlerFormulaDefaultClient;
import com.unideb.qsa.api.client.formulahandler.steps.DefaultFormulaHandlerFormulaStepsClient;
import com.unideb.qsa.api.client.formulahandler.steps.FormulaHandlerFormulaStepsClient;

/**
 * Configuration class for formula-handler client.
 */
@Configuration
public class FormulaHandlerClientConfig {

    @Autowired
    private Gateway<String> formulaDefaultFormulaHandlerGateway;
    @Autowired
    private Gateway<List<String>> formulaStepsFormulaHandlerGateway;
    @Autowired
    private Gateway<List<String>> formulaCalculatedFormulaHandlerGateway;
    @Autowired
    private UriCreator formulaHandlerFormulaDefaultUriCreator;
    @Autowired
    private UriCreator formulaHandlerFormulaStepsUriCreator;
    @Autowired
    private UriCreator formulaHandlerFormulaCalculatedUriCreator;
    @Autowired
    private HeaderFactory compositeRequestHeadersFactory;

    @Bean
    public FormulaHandlerFormulaDefaultClient formulaHandlerFormulaDefaultClient() {
        return new DefaultFormulaHandlerFormulaDefaultClient(new GenericClient<>(formulaDefaultFormulaHandlerGateway, compositeRequestHeadersFactory),
                formulaHandlerFormulaDefaultUriCreator);
    }

    @Bean
    public FormulaHandlerFormulaStepsClient formulaHandlerFormulaStepsClient() {
        return new DefaultFormulaHandlerFormulaStepsClient(new GenericClient<>(formulaStepsFormulaHandlerGateway, compositeRequestHeadersFactory),
                formulaHandlerFormulaStepsUriCreator);
    }

    @Bean
    public FormulaHandlerFormulaCalculatedClient formulaHandlerFormulaCalculatedClient() {
        return new DefaultFormulaHandlerFormulaCalculatedClient(new GenericClient<>(formulaCalculatedFormulaHandlerGateway, compositeRequestHeadersFactory),
                formulaHandlerFormulaCalculatedUriCreator);
    }
}

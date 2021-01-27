package com.unideb.qsa.api.client.calculator.system;

import java.util.List;

import com.unideb.qsa.api.client.config.UriCreator;
import com.unideb.qsa.api.client.config.client.GenericClient;
import com.unideb.qsa.api.domain.api.response.SystemElement;

/**
 * Default implementation of system element retrieving.
 */
public class DefaultCalculatorSystemElementClient implements CalculatorSystemElementClient {


    private final GenericClient<List<SystemElement>> genericClient;
    private final UriCreator uriCreator;

    public DefaultCalculatorSystemElementClient(GenericClient<List<SystemElement>> genericClient, UriCreator uriCreator) {
        this.genericClient = genericClient;
        this.uriCreator = uriCreator;
    }

    @Override
    public List<SystemElement> getSystems(List<String> systemIds) {
        return genericClient.call(systemIds, uriCreator.createUri());
    }
}

package com.unideb.qsa.api.client.formulahandler.steps;

import java.net.URI;

import com.unideb.qsa.api.client.config.UriCreator;
import com.unideb.qsa.api.client.config.client.GenericClient;

/**
 * Default implementation of steps output feature formula calculation.
 */
public class DefaultFormulaHandlerFormulaStepsClient implements FormulaHandlerFormulaStepsClient {

    private static final Object EMPTY_REQUEST_BODY = null;

    private final UriCreator uriCreator;
    private final GenericClient<String> genericClient;

    public DefaultFormulaHandlerFormulaStepsClient(GenericClient<String> genericClient, UriCreator uriCreator) {
        this.uriCreator = uriCreator;
        this.genericClient = genericClient;
    }

    @Override
    public String getFormulaSteps(String systemId, String featureId) {
        URI uri = uriCreator.createUri(systemId, featureId);
        return genericClient.call(EMPTY_REQUEST_BODY, uri);
    }
}

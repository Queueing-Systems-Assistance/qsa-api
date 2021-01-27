package com.unideb.qsa.api.client.formulahandler.steps;

import java.net.URI;
import java.util.List;

import com.unideb.qsa.api.client.config.UriCreator;
import com.unideb.qsa.api.client.config.client.GenericClient;

/**
 * Default implementation of steps output feature formula calculation.
 */
public class DefaultFormulaHandlerFormulaStepsClient implements FormulaHandlerFormulaStepsClient {

    private static final Object EMPTY_REQUEST_BODY = null;

    private final UriCreator uriCreator;
    private final GenericClient<List<String>> genericClient;

    public DefaultFormulaHandlerFormulaStepsClient(GenericClient<List<String>> genericClient, UriCreator uriCreator) {
        this.uriCreator = uriCreator;
        this.genericClient = genericClient;
    }

    @Override
    public List<String> getFormulaSteps(String systemId, String featureId) {
        URI uri = uriCreator.createUri(systemId, featureId);
        return genericClient.call(EMPTY_REQUEST_BODY, uri);
    }
}

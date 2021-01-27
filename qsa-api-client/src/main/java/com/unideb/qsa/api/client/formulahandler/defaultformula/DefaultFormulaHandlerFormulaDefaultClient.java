package com.unideb.qsa.api.client.formulahandler.defaultformula;


import java.net.URI;
import java.util.List;

import com.unideb.qsa.api.client.config.UriCreator;
import com.unideb.qsa.api.client.config.client.GenericClient;

/**
 * Default implementation of default output feature formula calculation.
 */
public class DefaultFormulaHandlerFormulaDefaultClient implements FormulaHandlerFormulaDefaultClient {

    private static final Object EMPTY_REQUEST_BODY = null;

    private final UriCreator uriCreator;
    private final GenericClient<String> genericClient;

    public DefaultFormulaHandlerFormulaDefaultClient(GenericClient<String> genericClient, UriCreator uriCreator) {
        this.uriCreator = uriCreator;
        this.genericClient = genericClient;
    }

    @Override
    public List<String> getFormulaDefault(String systemId, String featureId) {
        URI uri = uriCreator.createUri(systemId, featureId);
        String response = genericClient.call(EMPTY_REQUEST_BODY, uri);
        return List.of(response);
    }
}

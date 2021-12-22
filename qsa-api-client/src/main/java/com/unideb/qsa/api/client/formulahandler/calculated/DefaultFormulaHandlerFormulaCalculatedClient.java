package com.unideb.qsa.api.client.formulahandler.calculated;

import java.net.URI;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.unideb.qsa.api.client.config.UriCreator;
import com.unideb.qsa.api.client.config.client.GenericClient;
import com.unideb.qsa.api.domain.api.request.FeatureCondition;

/**
 * Default implementation of calculated output feature formula calculation.
 */
public class DefaultFormulaHandlerFormulaCalculatedClient implements FormulaHandlerFormulaCalculatedClient {


    private final UriCreator uriCreator;
    private final GenericClient<String> genericClient;

    public DefaultFormulaHandlerFormulaCalculatedClient(GenericClient<String> genericClient, UriCreator uriCreator) {
        this.uriCreator = uriCreator;
        this.genericClient = genericClient;
    }

    @Override
    public String getFormulaCalculated(String systemId, String featureId, List<FeatureCondition> featureConditions) {
        URI uri = uriCreator.createUri(systemId, featureId);
        Map<String, Double> requestBody = featureConditions.stream().collect(Collectors.toMap(FeatureCondition::getId, FeatureCondition::getValue));
        return genericClient.call(requestBody, uri);
    }
}

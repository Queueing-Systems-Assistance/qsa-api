package com.unideb.qsa.api.client.calculator.output;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import com.unideb.qsa.api.client.config.UriCreator;
import com.unideb.qsa.api.client.config.client.GenericClient;
import com.unideb.qsa.api.domain.api.request.FeatureCondition;
import com.unideb.qsa.api.domain.api.response.OutputFeature;
import com.unideb.qsa.api.domain.calculator.request.OutputFeatureRequest;

/**
 * Default implementation of system output features calculation.
 */
public class DefaultCalculatorOutputFeatureClient implements CalculatorOutputFeatureClient {

    private final GenericClient<List<OutputFeature>> genericClient;
    private final UriCreator uriCreator;

    public DefaultCalculatorOutputFeatureClient(GenericClient<List<OutputFeature>> genericClient, UriCreator uriCreator) {
        this.genericClient = genericClient;
        this.uriCreator = uriCreator;
    }

    @Override
    public List<OutputFeature> getOutputFeatures(String systemId, List<FeatureCondition> featureConditions, List<String> outputFeatureIds) {
        OutputFeatureRequest outputFeatureRequest = createOutputFeatureRequest(featureConditions, outputFeatureIds);
        URI uri = uriCreator.createUri(systemId);
        return genericClient.call(outputFeatureRequest, uri);
    }

    private OutputFeatureRequest createOutputFeatureRequest(List<FeatureCondition> featureConditions, List<String> outputFeatureIds) {
        var outputFeatureRequest = new OutputFeatureRequest();
        outputFeatureRequest.setOutputFeatureIds(outputFeatureIds);
        outputFeatureRequest.setFeatureConditions(featureConditions.stream().collect(Collectors.toMap(FeatureCondition::getId, FeatureCondition::getValue)));
        return outputFeatureRequest;
    }
}

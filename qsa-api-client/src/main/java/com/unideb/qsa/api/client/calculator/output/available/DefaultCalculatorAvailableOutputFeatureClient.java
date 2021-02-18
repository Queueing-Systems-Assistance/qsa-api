package com.unideb.qsa.api.client.calculator.output.available;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import com.unideb.qsa.api.client.config.UriCreator;
import com.unideb.qsa.api.client.config.client.GenericClient;
import com.unideb.qsa.api.domain.api.response.OutputFeature;

/**
 * Default implementation for resolving available system output feature.
 */
public class DefaultCalculatorAvailableOutputFeatureClient implements CalculatorAvailableOutputFeatureClient {

    private final GenericClient<List<OutputFeature>> genericClient;
    private final UriCreator uriCreator;

    public DefaultCalculatorAvailableOutputFeatureClient(GenericClient<List<OutputFeature>> genericClient, UriCreator uriCreator) {
        this.genericClient = genericClient;
        this.uriCreator = uriCreator;
    }

    @Override
    public List<OutputFeature> getAvailableOutputFeatures(String systemId, List<String> outputFeatureIds) {
        URI uri = uriCreator.createUri(systemId);
        return genericClient.call(Optional.ofNullable(outputFeatureIds).orElse(List.of()), uri);
    }
}

package com.unideb.qsa.api.client.calculator.output.stream;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import com.unideb.qsa.api.client.config.UriCreator;
import com.unideb.qsa.api.client.config.client.GenericClient;
import com.unideb.qsa.api.domain.api.request.FeatureCondition;
import com.unideb.qsa.api.domain.api.request.StreamOutput;
import com.unideb.qsa.api.domain.api.response.StreamOutputFeature;
import com.unideb.qsa.api.domain.calculator.request.StreamOutputFeatureRequest;

/**
 * Default implementation of streamed system output features calculation.
 */
public class DefaultCalculatorStreamOutputClient implements CalculatorStreamOutputFeatureClient {

    private final GenericClient<StreamOutputFeature> genericClient;
    private final UriCreator uriCreator;

    public DefaultCalculatorStreamOutputClient(GenericClient<StreamOutputFeature> genericClient, UriCreator uriCreator) {
        this.genericClient = genericClient;
        this.uriCreator = uriCreator;
    }


    @Override
    public StreamOutputFeature getStreamOutputFeatures(String systemId, StreamOutput streamOutput,
            List<FeatureCondition> featureConditions, List<String> outputFeatureIds) {
        StreamOutputFeatureRequest request = createRequest(streamOutput, featureConditions, outputFeatureIds);
        URI uri = uriCreator.createUri(systemId);
        return genericClient.call(request, uri);
    }

    private StreamOutputFeatureRequest createRequest(StreamOutput streamOutput, List<FeatureCondition> featureConditions, List<String> outputFeatureIds) {
        var streamOutputFeatureRequest = new StreamOutputFeatureRequest();
        streamOutputFeatureRequest.setStreamOutput(streamOutput);
        streamOutputFeatureRequest
                .setFeatureConditions(featureConditions.stream().collect(Collectors.toMap(FeatureCondition::getId, FeatureCondition::getValue)));
        streamOutputFeatureRequest.setOutputFeatureIds(outputFeatureIds);
        return streamOutputFeatureRequest;
    }
}

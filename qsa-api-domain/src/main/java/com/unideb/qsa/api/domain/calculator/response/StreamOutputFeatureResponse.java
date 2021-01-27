package com.unideb.qsa.api.domain.calculator.response;

import java.util.List;

import com.unideb.qsa.api.domain.api.response.OutputFeature;

/**
 * Response which contains calculated streamed system output features.
 */
public class StreamOutputFeatureResponse {

    private List<OutputFeature> outputFeatures;
    private List<Double> stream;

    public List<Double> getStream() {
        return stream;
    }

    public void setStream(final List<Double> stream) {
        this.stream = stream;
    }

    public List<OutputFeature> getOutputFeatures() {
        return outputFeatures;
    }

    public void setOutputFeatures(final List<OutputFeature> outputFeatures) {
        this.outputFeatures = outputFeatures;
    }

}

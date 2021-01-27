package com.unideb.qsa.api.domain.api.response;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * Represents streamed, calculated system features.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class StreamOutputFeature {

    private List<OutputFeature> outputFeatures;
    private List<Float> stream;

    public List<OutputFeature> getOutputFeatures() {
        return Optional.ofNullable(outputFeatures).orElse(Collections.emptyList());
    }

    public void setOutputFeatures(final List<OutputFeature> outputFeatures) {
        this.outputFeatures = outputFeatures;
    }

    public List<Float> getStream() {
        return Optional.ofNullable(stream).orElse(Collections.emptyList());
    }

    public void setStream(final List<Float> stream) {
        this.stream = stream;
    }
}

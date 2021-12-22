package com.unideb.qsa.api.domain.calculator.request;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Request for calculating system output features.
 */
public class OutputFeatureRequest {

    private Map<String, Double> featureConditions;
    private List<String> outputFeatureIds = List.of();


    public Map<String, Double> getFeatureConditions() {
        return featureConditions;
    }

    public void setFeatureConditions(final Map<String, Double> featureConditions) {
        this.featureConditions = featureConditions;
    }

    public List<String> getOutputFeatureIds() {
        return Optional.ofNullable(outputFeatureIds).orElse(List.of());
    }

    public void setOutputFeatureIds(List<String> outputFeatureIds) {
        this.outputFeatureIds = Optional.ofNullable(outputFeatureIds).orElse(List.of());
    }

    @Override
    public String toString() {
        return "OutputFeatureRequest{"
               + "featureConditions=" + featureConditions
               + ", outputFeatureIds=" + outputFeatureIds
               + "}";
    }
}

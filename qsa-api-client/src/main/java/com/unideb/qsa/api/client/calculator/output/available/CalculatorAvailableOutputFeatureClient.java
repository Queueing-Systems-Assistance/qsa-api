package com.unideb.qsa.api.client.calculator.output.available;

import java.util.List;

import com.unideb.qsa.api.domain.api.response.OutputFeature;

/**
 * Interface for resolving available system output feature.
 */
public interface CalculatorAvailableOutputFeatureClient {

    /**
     * Get available system output features.
     * @param systemId          system id
     * @param outputFeatureIds  requested output features ids
     * @return available system output features
     */
    List<OutputFeature> getAvailableOutputFeatures(String systemId, List<String> outputFeatureIds);
}

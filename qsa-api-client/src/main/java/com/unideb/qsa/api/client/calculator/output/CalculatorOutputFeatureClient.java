package com.unideb.qsa.api.client.calculator.output;

import java.util.List;

import com.unideb.qsa.api.domain.api.request.FeatureCondition;
import com.unideb.qsa.api.domain.api.response.OutputFeature;

/**
 * Interface for system output feature calculation.
 */
public interface CalculatorOutputFeatureClient {

    /**
     * Calculate system output features.
     * @param systemId          system id
     * @param featureConditions input features
     * @param outputFeatureIds  requested output features ids
     * @return calculated system output features
     */
    List<OutputFeature> getOutputFeatures(String systemId, List<FeatureCondition> featureConditions, List<String> outputFeatureIds);

}

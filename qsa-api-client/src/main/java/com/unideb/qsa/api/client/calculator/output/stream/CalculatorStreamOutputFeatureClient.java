package com.unideb.qsa.api.client.calculator.output.stream;

import java.util.List;

import com.unideb.qsa.api.domain.api.request.FeatureCondition;
import com.unideb.qsa.api.domain.api.request.StreamOutput;
import com.unideb.qsa.api.domain.api.response.StreamOutputFeature;

/**
 * Interface for streamed system output feature calculation.
 */
public interface CalculatorStreamOutputFeatureClient {

    /**
     * Calculate streamed system output features.
     * @param systemId          system id
     * @param streamOutput      stream
     * @param featureConditions input features
     * @param outputFeatureIds  requested output features ids
     * @return calculated stream system output features
     */
    StreamOutputFeature getStreamOutputFeatures(String systemId, StreamOutput streamOutput,
            List<FeatureCondition> featureConditions, List<String> outputFeatureIds);
}

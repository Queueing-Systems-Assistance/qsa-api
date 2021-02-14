package com.unideb.qsa.api.implementation.facade;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.unideb.qsa.api.client.calculator.output.CalculatorOutputFeatureClient;
import com.unideb.qsa.api.client.calculator.output.available.CalculatorAvailableOutputFeatureClient;
import com.unideb.qsa.api.client.calculator.output.stream.CalculatorStreamOutputFeatureClient;
import com.unideb.qsa.api.domain.api.request.FeatureCondition;
import com.unideb.qsa.api.domain.api.request.StreamOutput;
import com.unideb.qsa.api.domain.api.response.OutputFeature;
import com.unideb.qsa.api.domain.api.response.StreamOutputFeature;
import com.unideb.qsa.api.domain.api.response.SystemElement;

/**
 * Facade for calculating system output features.
 */
@Component
public class OutputFeatureCalculationFacade {

    @Autowired
    private CalculatorOutputFeatureClient calculatorOutputFeatureClient;
    @Autowired
    private CalculatorStreamOutputFeatureClient calculatorStreamOutputFeatureClient;
    @Autowired
    private CalculatorAvailableOutputFeatureClient calculatorAvailableOutputFeatureClient;

    /**
     * Calculates system output features.
     * @param systemElement             resolved system element
     * @param featureConditions         input features
     * @param requestedOutputFeatureIds requested output feature ids
     * @return calculated output features
     */
    public List<OutputFeature> getOutputs(SystemElement systemElement, List<FeatureCondition> featureConditions, List<String> requestedOutputFeatureIds) {
        return calculatorOutputFeatureClient.getOutputFeatures(systemElement.getId(), featureConditions, requestedOutputFeatureIds);
    }

    /**
     * Calculates system output features.
     * @param systemElement             resolved system element
     * @param featureConditions         input features
     * @param requestedOutputFeatureIds requested output feature ids
     * @return calculated streamed output features
     */
    public StreamOutputFeature getOutputsStream(SystemElement systemElement, List<FeatureCondition> featureConditions, List<String> requestedOutputFeatureIds,
            StreamOutput streamOutput) {
        return calculatorStreamOutputFeatureClient.getStreamOutputFeatures(systemElement.getId(), streamOutput, featureConditions, requestedOutputFeatureIds);
    }

    /**
     * Get available system output features.
     * @param systemElement             resolved system element
     * @param requestedOutputFeatureIds requested output feature ids
     * @return calculated output features
     */
    public List<OutputFeature> getAvailableOutputs(SystemElement systemElement, List<String> requestedOutputFeatureIds) {
        return calculatorAvailableOutputFeatureClient.getAvailableOutputFeatures(systemElement.getId(), requestedOutputFeatureIds);
    }
}

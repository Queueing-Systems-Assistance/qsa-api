package com.unideb.qsa.api.implementation.graphql;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.coxautodev.graphql.tools.GraphQLResolver;

import com.unideb.qsa.api.domain.api.request.FeatureCondition;
import com.unideb.qsa.api.domain.api.request.FormulaType;
import com.unideb.qsa.api.domain.api.request.StreamOutput;
import com.unideb.qsa.api.domain.api.response.OutputFeature;
import com.unideb.qsa.api.domain.api.response.StreamOutputFeature;
import com.unideb.qsa.api.domain.api.response.SystemElement;
import com.unideb.qsa.api.implementation.facade.FormulaCalculationFacade;
import com.unideb.qsa.api.implementation.facade.OutputFeatureCalculationFacade;

/**
 * Resolver to calculate system additional elements. This class called after assembling {@link SystemElement} class.
 */
@Component
public class SystemGraphQLResolver implements GraphQLResolver<SystemElement> {

    @Autowired
    private OutputFeatureCalculationFacade outputFeatureCalculationFacade;
    @Autowired
    private FormulaCalculationFacade formulaCalculationFacade;

    /**
     * Calculates system output features.
     * @param systemElement             resolved system element
     * @param featureConditions         input features
     * @param requestedOutputFeatureIds requested output feature ids
     * @return calculated output features
     */
    public List<OutputFeature> getOutputs(SystemElement systemElement, List<FeatureCondition> featureConditions, List<String> requestedOutputFeatureIds) {
        return outputFeatureCalculationFacade.getOutputs(systemElement, featureConditions, requestedOutputFeatureIds);
    }

    /**
     * Get available system output features.
     * @param systemElement             resolved system element
     * @param requestedOutputFeatureIds requested output feature ids
     * @return Output features
     */
    public List<OutputFeature> getAvailableOutputs(SystemElement systemElement, List<String> requestedOutputFeatureIds) {
        return outputFeatureCalculationFacade.getAvailableOutputs(systemElement, requestedOutputFeatureIds);
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
        return outputFeatureCalculationFacade.getOutputsStream(systemElement, featureConditions, requestedOutputFeatureIds, streamOutput);
    }

    /**
     * Resolves a formula based on the system, inputs and output.
     * @param systemElement     resolved system element
     * @param featureId         output feature id
     * @param formulaType       type of the requested formula
     * @param featureConditions input features
     * @return resolved formulas.
     */
    public String getFormula(SystemElement systemElement, String featureId, List<FeatureCondition> featureConditions, FormulaType formulaType) {
        return formulaCalculationFacade.getFormula(systemElement, featureId, featureConditions, formulaType);
    }
}

package com.unideb.qsa.api.implementation.graphql;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsData;
import com.netflix.graphql.dgs.DgsDataFetchingEnvironment;
import com.netflix.graphql.dgs.InputArgument;

import com.unideb.qsa.api.domain.api.request.FeatureCondition;
import com.unideb.qsa.api.domain.api.response.OutputFeature;
import com.unideb.qsa.api.domain.api.response.SystemElement;
import com.unideb.qsa.api.implementation.facade.OutputFeatureCalculationFacade;

/**
 * Fetches a system outputs.
 * This class called after assembling {@link SystemElement}.
 */
@DgsComponent
public class OutputGraphQLFetcher {

    @Autowired
    private OutputFeatureCalculationFacade outputFeatureCalculationFacade;

    /**
     * Calculates system output features.
     * @param environment               current request context
     * @param inputFeatureConditions    input features
     * @param requestedOutputFeatureIds requested output feature ids
     * @return calculated output features
     */
    @DgsData(parentType = "SystemElement", field = "outputs")
    public List<OutputFeature> getOutputs(
            DgsDataFetchingEnvironment environment,
            @InputArgument(collectionType = FeatureCondition.class) List<FeatureCondition> inputFeatureConditions,
            @InputArgument List<String> requestedOutputFeatureIds) {
        SystemElement systemElement = environment.getSource();
        return outputFeatureCalculationFacade.getOutputs(systemElement, inputFeatureConditions, requestedOutputFeatureIds);
    }
}

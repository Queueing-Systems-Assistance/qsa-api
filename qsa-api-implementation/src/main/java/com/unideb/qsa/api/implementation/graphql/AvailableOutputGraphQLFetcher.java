package com.unideb.qsa.api.implementation.graphql;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsData;
import com.netflix.graphql.dgs.DgsDataFetchingEnvironment;
import com.netflix.graphql.dgs.InputArgument;

import com.unideb.qsa.api.domain.api.response.OutputFeature;
import com.unideb.qsa.api.domain.api.response.SystemElement;
import com.unideb.qsa.api.implementation.facade.OutputFeatureCalculationFacade;

/**
 * Fetches a system available outputs.
 * This class called after assembling {@link SystemElement}.
 */
@DgsComponent
public class AvailableOutputGraphQLFetcher {

    @Autowired
    private OutputFeatureCalculationFacade outputFeatureCalculationFacade;

    /**
     * Get available system output features.
     * @param environment               current request context
     * @param requestedOutputFeatureIds requested output feature ids
     * @return Output features
     */
    @DgsData(parentType = "SystemElement", field = "availableOutputs")
    public List<OutputFeature> getAvailableOutputs(
            DgsDataFetchingEnvironment environment,
            @InputArgument List<String> requestedOutputFeatureIds) {
        SystemElement systemElement = environment.getSource();
        return outputFeatureCalculationFacade.getAvailableOutputs(systemElement, requestedOutputFeatureIds);
    }
}

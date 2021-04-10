package com.unideb.qsa.api.implementation.graphql;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsData;
import com.netflix.graphql.dgs.DgsDataFetchingEnvironment;
import com.netflix.graphql.dgs.InputArgument;

import com.unideb.qsa.api.domain.api.request.FeatureCondition;
import com.unideb.qsa.api.domain.api.request.StreamOutput;
import com.unideb.qsa.api.domain.api.response.StreamOutputFeature;
import com.unideb.qsa.api.domain.api.response.SystemElement;
import com.unideb.qsa.api.implementation.facade.OutputFeatureCalculationFacade;

/**
 * Fetches a system streamed outputs.
 * This class called after assembling {@link SystemElement}.
 */
@DgsComponent
public class OutputsStreamGraphQLFetcher {

    @Autowired
    private OutputFeatureCalculationFacade outputFeatureCalculationFacade;

    /**
     * Calculates streamed system output features.
     * @param environment               current request context
     * @param inputFeatureConditions    input features
     * @param requestedOutputFeatureIds requested output feature ids
     * @param stream                    requested stream
     * @return calculated streamed output features
     */
    @DgsData(parentType = "SystemElement", field = "outputsStream")
    public StreamOutputFeature getOutputsStream(
            DgsDataFetchingEnvironment environment,
            @InputArgument(collectionType = FeatureCondition.class) List<FeatureCondition> inputFeatureConditions,
            @InputArgument List<String> requestedOutputFeatureIds,
            @InputArgument StreamOutput stream) {
        SystemElement systemElement = environment.getSource();
        return outputFeatureCalculationFacade.getOutputsStream(systemElement, inputFeatureConditions, requestedOutputFeatureIds, stream);
    }
}

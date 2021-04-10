package com.unideb.qsa.api.implementation.graphql;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsData;
import com.netflix.graphql.dgs.DgsDataFetchingEnvironment;
import com.netflix.graphql.dgs.InputArgument;

import com.unideb.qsa.api.domain.api.request.FeatureCondition;
import com.unideb.qsa.api.domain.api.request.FormulaType;
import com.unideb.qsa.api.domain.api.response.SystemElement;
import com.unideb.qsa.api.implementation.facade.FormulaCalculationFacade;

/**
 * Fetches a formula based on a system feature id.
 * This class called after assembling {@link SystemElement}.
 */
@DgsComponent
public class FormulaGraphQLFetcher {

    @Autowired
    private FormulaCalculationFacade formulaCalculationFacade;

    /**
     * Resolves a formula based on the system, inputs and output.
     * @param environment            current request context
     * @param featureId              output feature id
     * @param formulaType            type of the requested formula
     * @param inputFeatureConditions input features
     * @return resolved formulas.
     */
    @DgsData(parentType = "SystemElement", field = "formula")
    public String getFormula(
            DgsDataFetchingEnvironment environment,
            @InputArgument String featureId,
            @InputArgument(collectionType = FeatureCondition.class) List<FeatureCondition> inputFeatureConditions,
            @InputArgument FormulaType formulaType) {
        SystemElement systemElement = environment.getSource();
        return formulaCalculationFacade.getFormula(systemElement, featureId, inputFeatureConditions, formulaType);
    }
}

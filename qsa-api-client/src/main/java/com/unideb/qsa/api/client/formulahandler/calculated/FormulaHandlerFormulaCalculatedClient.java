package com.unideb.qsa.api.client.formulahandler.calculated;

import java.util.List;

import com.unideb.qsa.api.domain.api.request.FeatureCondition;

/**
 * Interface for calculated output feature formula calculation.
 */
public interface FormulaHandlerFormulaCalculatedClient {


    /**
     * Calculate default output feature formula for a given system.
     * @param systemId  system id
     * @param featureId output feature id
     */
    String getFormulaCalculated(String systemId, String featureId, List<FeatureCondition> featureConditions);

}

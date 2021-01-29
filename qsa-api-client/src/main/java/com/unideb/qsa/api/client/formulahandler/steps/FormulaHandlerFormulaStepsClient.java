package com.unideb.qsa.api.client.formulahandler.steps;

/**
 * Interface for steps output feature formula calculation.
 */
public interface FormulaHandlerFormulaStepsClient {

    /**
     * Calculate steps output feature formula for a given system.
     * @param systemId  system id
     * @param featureId output feature id
     */
    String getFormulaSteps(String systemId, String featureId);
}

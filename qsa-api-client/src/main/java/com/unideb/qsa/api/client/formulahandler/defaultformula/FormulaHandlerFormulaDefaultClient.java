package com.unideb.qsa.api.client.formulahandler.defaultformula;

import java.util.List;

/**
 * Interface for default output feature formula calculation.
 */
public interface FormulaHandlerFormulaDefaultClient {

    /**
     * Calculate default output feature formula for a given system.
     * @param systemId  system id
     * @param featureId output feature id
     */
    List<String> getFormulaDefault(String systemId, String featureId);
}

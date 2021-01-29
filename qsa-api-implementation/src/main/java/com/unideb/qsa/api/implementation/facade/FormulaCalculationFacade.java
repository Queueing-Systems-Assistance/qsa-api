package com.unideb.qsa.api.implementation.facade;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.unideb.qsa.api.client.formulahandler.calculated.FormulaHandlerFormulaCalculatedClient;
import com.unideb.qsa.api.client.formulahandler.defaultformula.FormulaHandlerFormulaDefaultClient;
import com.unideb.qsa.api.client.formulahandler.steps.FormulaHandlerFormulaStepsClient;
import com.unideb.qsa.api.domain.api.request.FeatureCondition;
import com.unideb.qsa.api.domain.api.request.FormulaType;
import com.unideb.qsa.api.domain.api.response.SystemElement;

/**
 * Facade for formula calculation.
 */
@Component
public class FormulaCalculationFacade {

    @Autowired
    private FormulaHandlerFormulaCalculatedClient formulaHandlerFormulaCalculatedClient;
    @Autowired
    private FormulaHandlerFormulaDefaultClient formulaHandlerFormulaDefaultClient;
    @Autowired
    private FormulaHandlerFormulaStepsClient formulaHandlerFormulaStepsClient;

    /**
     * Calculates formula based on the system, feature and inputs.
     * @param systemElement     resolved system element
     * @param featureId         output feature id
     * @param featureConditions input features
     * @param formulaType       type of the requested formula
     * @return resolved formula
     */
    public String getFormula(SystemElement systemElement, String featureId, List<FeatureCondition> featureConditions, FormulaType formulaType) {
        String systemId = systemElement.getId();
        var result = "";
        switch(formulaType) {
            case DEFAULT:
                result = formulaHandlerFormulaDefaultClient.getFormulaDefault(systemId, featureId);
                break;
            case ELEMENTS:
                result = formulaHandlerFormulaStepsClient.getFormulaSteps(systemId, featureId);
                break;
            case CALCULATED:
                result = formulaHandlerFormulaCalculatedClient.getFormulaCalculated(systemId, featureId, featureConditions);
                break;
        }
        return result;
    }
}

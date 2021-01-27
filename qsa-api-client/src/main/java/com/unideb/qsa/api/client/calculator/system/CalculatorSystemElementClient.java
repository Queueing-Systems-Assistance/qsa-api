package com.unideb.qsa.api.client.calculator.system;

import java.util.List;

import com.unideb.qsa.api.domain.api.response.SystemElement;

/**
 * Interface for system element retrieving.
 */
public interface CalculatorSystemElementClient {

    /**
     * Gets all the available system elements.
     * @param systemIds system ids
     * @return a list of system elements
     */
    List<SystemElement> getSystems(List<String> systemIds);

}

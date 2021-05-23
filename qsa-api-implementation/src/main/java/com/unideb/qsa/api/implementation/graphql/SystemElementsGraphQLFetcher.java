package com.unideb.qsa.api.implementation.graphql;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;

import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsData;
import com.netflix.graphql.dgs.InputArgument;

import com.unideb.qsa.api.client.calculator.system.CalculatorSystemElementClient;
import com.unideb.qsa.api.domain.api.response.SystemElement;

/**
 * Fetches a system element.
 */
@DgsComponent
public class SystemElementsGraphQLFetcher {

    @Autowired
    private CalculatorSystemElementClient calculatorSystemElementClient;

    /**
     * Resolves system elements.
     * @param systemIds requested system ids
     * @return resolved {@link SystemElement}s.
     */
    @DgsData(parentType = "Query", field = "systemElements")
    public List<SystemElement> getSystemElements(
            @Nullable @InputArgument List<String> systemIds) {
        List<String> requestedSystemElements = resolveSystemIds(systemIds);
        return new ArrayList<>(calculatorSystemElementClient.getSystems(requestedSystemElements));
    }

    private List<String> resolveSystemIds(List<String> systemIds) {
        return Optional.ofNullable(systemIds).orElse(List.of());
    }
}

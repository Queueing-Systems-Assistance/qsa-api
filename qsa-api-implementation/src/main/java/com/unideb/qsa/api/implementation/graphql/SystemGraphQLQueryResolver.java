package com.unideb.qsa.api.implementation.graphql;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;

import graphql.schema.DataFetchingEnvironment;

import com.unideb.qsa.api.client.calculator.system.CalculatorSystemElementClient;
import com.unideb.qsa.api.domain.api.response.SystemElement;
import com.unideb.qsa.api.implementation.updater.LocaleUpdater;

/**
 * GraphQL query to resolve system elements.
 */
@Component
public class SystemGraphQLQueryResolver implements GraphQLQueryResolver {

    @Autowired
    private LocaleUpdater localeUpdater;
    @Autowired
    private CalculatorSystemElementClient calculatorSystemElementClient;

    /**
     * Resolves system elements.
     * @param systemIds requested system ids
     * @return resolved {@link SystemElement}s.
     */
    public List<SystemElement> getSystemElements(@Nullable List<String> systemIds, DataFetchingEnvironment environmentnv) {
        localeUpdater.updateLocale(environmentnv.getContext());
        List<String> requestedSystemElements = resolveSystemIds(systemIds);
        return new ArrayList<>(calculatorSystemElementClient.getSystems(requestedSystemElements));

    }

    private List<String> resolveSystemIds(List<String> systemIds) {
        return Optional.ofNullable(systemIds).orElse(List.of());
    }
}

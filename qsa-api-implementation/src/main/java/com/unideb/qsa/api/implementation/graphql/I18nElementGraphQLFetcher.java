package com.unideb.qsa.api.implementation.graphql;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsData;
import com.netflix.graphql.dgs.InputArgument;

import com.unideb.qsa.api.domain.api.response.I18nElement;
import com.unideb.qsa.api.implementation.facade.I18nElementCalculationFacade;

/**
 * GraphQL query to resolve i18n values.
 */
@DgsComponent
public class I18nElementGraphQLFetcher {

    @Autowired
    private I18nElementCalculationFacade i18nElementCalculationFacade;

    /**
     * Resolved i18n elements based on the given keys.
     * @param keys i18n keys
     * @return i18n elements with all the available locales
     */
    @DgsData(parentType = "Query", field = "i18nElements")
    public List<I18nElement> getI18nElements(
            @InputArgument List<String> keys) {
        return i18nElementCalculationFacade.getI8nElements(keys);
    }
}

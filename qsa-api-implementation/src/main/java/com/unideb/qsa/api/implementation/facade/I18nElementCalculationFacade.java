package com.unideb.qsa.api.implementation.facade;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.unideb.qsa.api.client.i18nservice.keys.I18nServiceKeysClient;
import com.unideb.qsa.api.domain.api.response.I18nElement;
import com.unideb.qsa.api.domain.api.response.I18nValue;
import com.unideb.qsa.api.domain.i18nservice.response.I18nElementResponse;

/**
 * Facade to resolve i18n elements.
 */
@Component
public class I18nElementCalculationFacade {

    @Autowired
    private I18nServiceKeysClient i18nServiceKeysClient;

    /**
     * Gets i18n elements based on the given i18n keys.
     * @param keys i18n keys
     * @return resolved i18n elements with all the locales.
     */
    public List<I18nElement> getI8nElements(List<String> keys) {
        List<I18nElementResponse> i18nElements = i18nServiceKeysClient.getI18nElements(keys);
        return i18nElements.stream().map(this::createI18nElement).collect(Collectors.toList());
    }

    private I18nElement createI18nElement(I18nElementResponse i18nElementResponse) {
        var i18nElement = new I18nElement();
        i18nElement.setKey(i18nElementResponse.getKey());
        List<I18nValue> i18nValues = i18nElementResponse.getValue().keySet().stream()
                                                        .map(key -> createI18nValue(i18nElementResponse, key))
                                                        .collect(Collectors.toList());
        i18nElement.setValues(i18nValues);
        return i18nElement;
    }

    private I18nValue createI18nValue(I18nElementResponse i18nElementResponse, String key) {
        var i18nValue = new I18nValue();
        i18nValue.setLocale(key);
        i18nValue.setI18n(i18nElementResponse.getValue().get(key));
        return i18nValue;
    }

}

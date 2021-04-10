package com.unideb.qsa.api.domain.api.response;

import java.util.List;

/**
 * Domain class for an i18n element.
 */
public class I18nElement {

    private String key;
    private List<I18nValue> values;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public List<I18nValue> getValues() {
        return values;
    }

    public void setValues(List<I18nValue> values) {
        this.values = values;
    }
}

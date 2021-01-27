package com.unideb.qsa.api.domain.api.request;

/**
 * Domain class for features. Use as key-value pairs, eg.: Lambda=2.
 */
public class FeatureCondition {

    private String value;
    private String id;

    public String getId() {
        return id;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "FeatureCondition{"
               + "value='" + value + "'"
               + ", id='" + id + "'"
               + "}";
    }
}

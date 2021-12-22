package com.unideb.qsa.api.domain.api.request;

/**
 * Domain class for features. Use as key-value pairs, eg.: Lambda=2.
 */
public class FeatureCondition {

    private Double value;
    private String id;

    public String getId() {
        return id;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(final Double value) {
        this.value = value;
    }

    public void setId(final String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "FeatureCondition{"
               + "value='" + value + "'"
               + ", id='" + id + "'"
               + "}";
    }
}

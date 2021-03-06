package com.unideb.qsa.api.domain.api.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * Represents a system input feature.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class InputFeature {

    private String id;
    private String name;
    private String description;
    private boolean typeFraction;
    private InputGroup inputGroup;

    public String getDescription() {
        return description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    public boolean isTypeFraction() {
        return typeFraction;
    }

    public void setTypeFraction(final boolean typeFraction) {
        this.typeFraction = typeFraction;
    }

    public InputGroup getInputGroup() {
        return inputGroup;
    }

    public void setInputGroup(final InputGroup inputGroup) {
        this.inputGroup = inputGroup;
    }

    public String getId() {
        return id;
    }

    public void setId(final String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }
}

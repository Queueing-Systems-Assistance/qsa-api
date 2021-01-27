package com.unideb.qsa.api.domain.api.response;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * Represents a system.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SystemElement {

    private String name;
    private String id;
    private String status;
    private String description;
    private List<InputFeature> inputs;
    private List<OutputFeature> outputs;

    public SystemElement() {
    }

    public List<OutputFeature> getOutputs() {
        return outputs;
    }

    public void setOutputs(final List<OutputFeature> outputs) {
        this.outputs = outputs;
    }

    public List<InputFeature> getInputs() {
        return Optional.ofNullable(inputs).orElse(Collections.emptyList());
    }

    public void setInputs(final List<InputFeature> inputs) {
        this.inputs = inputs;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(final String description) {
        this.description = description;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(final String status) {
        this.status = status;
    }
}

package com.unideb.qsa.api.domain.api.request;

/**
 * Domain class for streaming the system features. This class is the base, contains the information from how to stream the features: which is the stream
 * feature, where to start, end and how big the steps between two calculations.
 */
public class StreamOutput {

    private String featureId;
    private Float from;
    private Float to;
    private Float steps;

    public String getFeatureId() {
        return featureId;
    }

    public void setFeatureId(final String featureId) {
        this.featureId = featureId;
    }

    public Float getFrom() {
        return from;
    }

    public void setFrom(final Float from) {
        this.from = from;
    }

    public Float getSteps() {
        return steps;
    }

    public void setSteps(final Float steps) {
        this.steps = steps;
    }

    public Float getTo() {
        return to;
    }

    public void setTo(final Float to) {
        this.to = to;
    }

    @Override
    public String toString() {
        return "StreamOutput{"
               + "featureId='" + featureId + "'"
               + ", from=" + from
               + ", to=" + to
               + ", steps=" + steps
               + "}";
    }
}

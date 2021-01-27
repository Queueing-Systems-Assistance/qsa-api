package com.unideb.qsa.api.domain.calculator.request;

import com.unideb.qsa.api.domain.api.request.StreamOutput;

/**
 * Request for calculating streamed system output features.
 */
public class StreamOutputFeatureRequest extends OutputFeatureRequest {

    private StreamOutput streamOutput;

    public StreamOutput getStreamOutput() {
        return streamOutput;
    }

    public void setStreamOutput(final StreamOutput streamOutput) {
        this.streamOutput = streamOutput;
    }

    @Override
    public String toString() {
        return "StreamOutputFeatureRequest{"
               + "streamOutput=" + streamOutput
               + "} " + super.toString();
    }
}

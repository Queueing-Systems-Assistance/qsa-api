package com.unideb.qsa.api.client.config.header.factory;

import java.util.Collections;
import java.util.Map;

import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

/**
 * Factory class for creating static header values.
 */
public class StaticValueHeadersFactory implements HeaderFactory {

    private final Map<String, String> headers;

    public StaticValueHeadersFactory(Map<String, String> headers) {
        this.headers = Collections.unmodifiableMap(headers);
    }

    @Override
    public MultiValueMap<String, String> createHeaders() {
        MultiValueMap<String, String> httpHeaders = new LinkedMultiValueMap<>();
        httpHeaders.setAll(headers);
        return httpHeaders;
    }
}

package com.unideb.qsa.api.client.config.header.factory;

import java.util.Collections;
import java.util.Map;
import java.util.function.Supplier;

import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

/**
 * Factory class for creating dynamic header values primarily for those which are bound to the request's thread.
 */
public class DynamicValueHeaderFactory implements HeaderFactory {

    private final Map<String, Supplier<String>> supplierByHeaderName;

    public DynamicValueHeaderFactory(Map<String, Supplier<String>> supplierByHeaderName) {
        this.supplierByHeaderName = Collections.unmodifiableMap(supplierByHeaderName);
    }

    @Override
    public MultiValueMap<String, String> createHeaders() {
        MultiValueMap<String, String> httpHeaders = new LinkedMultiValueMap<>();
        supplierByHeaderName.forEach((name, supplier) -> httpHeaders.add(name, supplier.get()));
        return httpHeaders;
    }
}

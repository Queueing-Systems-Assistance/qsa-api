package com.unideb.qsa.api.client.config.header.factory;

import org.springframework.util.MultiValueMap;

/**
 * Interface abstraction for HTTP request header creation.
 */
@FunctionalInterface
public interface HeaderFactory {

    /**
     * Creates request headers.
     * @return the created headers
     */
    MultiValueMap<String, String> createHeaders();
}

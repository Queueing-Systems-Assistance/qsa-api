package com.unideb.qsa.api.client.i18nservice;

import java.net.URI;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.unideb.qsa.api.client.config.UriCreator;

/**
 * Configuration for i18n-service {@link URI}s.
 */
@Configuration
public class I18nServiceUriCreatorConfig {

    @Value("${client.i18n-service.scheme}")
    private String scheme;
    @Value("${client.i18n-service.port}")
    private String port;
    @Value("${client.i18n-service.host}")
    private String host;
    @Value("${client.i18n-service.endpoint.keys}")
    private String endpointKeys;

    @Bean
    public UriCreator i18nServiceKeysUriCreator() {
        return new UriCreator(scheme, host, port, endpointKeys);
    }
}

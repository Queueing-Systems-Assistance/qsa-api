package com.unideb.qsa.api.client.i18nservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.unideb.qsa.api.client.config.UriCreator;
import com.unideb.qsa.api.client.config.client.GenericClient;
import com.unideb.qsa.api.client.config.gateway.Gateway;
import com.unideb.qsa.api.client.config.header.factory.HeaderFactory;
import com.unideb.qsa.api.client.i18nservice.keys.DefaultI18nServiceKeysClient;
import com.unideb.qsa.api.client.i18nservice.keys.I18nServiceKeysClient;
import com.unideb.qsa.api.domain.i18nservice.response.I18nElementResponse;

/**
 * Configuration class for i18n-service client.
 */
@Configuration
public class I18nServiceClientConfig {


    @Autowired
    private Gateway<List<I18nElementResponse>> i18nServiceKeysGateway;
    @Autowired
    private UriCreator i18nServiceKeysUriCreator;
    @Autowired
    private HeaderFactory compositeRequestHeadersFactory;

    @Bean
    public I18nServiceKeysClient i18nServiceKeysClient() {
        return new DefaultI18nServiceKeysClient(new GenericClient<>(i18nServiceKeysGateway, compositeRequestHeadersFactory), i18nServiceKeysUriCreator);
    }
}

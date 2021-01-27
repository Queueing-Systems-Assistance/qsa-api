package com.unideb.qsa.api.client.config.header;

import static org.springframework.http.MediaType.APPLICATION_JSON;

import java.util.Map;

import org.slf4j.MDC;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;

import com.unideb.qsa.api.client.config.header.factory.DynamicValueHeaderFactory;
import com.unideb.qsa.api.client.config.header.factory.HeaderFactory;
import com.unideb.qsa.api.client.config.header.factory.StaticValueHeadersFactory;

/**
 * Configuration for header factories.
 */
@Configuration
public class HeaderFactoryConfig {

    private static final String HEADER_NAME_REQUEST_ID = "X-Request-Id";

    @Bean
    public HeaderFactory acceptLanguageHeaderFactory() {
        return new DynamicValueHeaderFactory(Map.of(HttpHeaders.ACCEPT_LANGUAGE, () -> LocaleContextHolder.getLocale().toString()));
    }

    @Bean
    public HeaderFactory requestIdHeaderFactory() {
        return new DynamicValueHeaderFactory(Map.of(HEADER_NAME_REQUEST_ID, () -> MDC.get("requestId")));
    }

    @Bean
    public HeaderFactory jsonHttpHeaderFactory() {
        return new StaticValueHeadersFactory(Map.of(
                HttpHeaders.CONTENT_TYPE, APPLICATION_JSON.toString(),
                HttpHeaders.ACCEPT, APPLICATION_JSON.toString()
        ));
    }
}

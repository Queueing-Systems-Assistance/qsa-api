package com.unideb.qsa.api.server.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * CORS configuration.
 */
@Configuration
public class CorsConfig {

    private static final Logger LOG = LoggerFactory.getLogger(ServerConfig.class);

    @Value("${graphql.endpoint:/graphql}")
    private String endpoint;

    @Bean
    @Profile(value = "dev | lab")
    public FilterRegistrationBean<CorsFilter> corsFilter() {
        LOG.info("Creating CORS filters [endpoint={}]", endpoint);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOrigin("*");
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");
        source.registerCorsConfiguration(endpoint, config);
        FilterRegistrationBean<CorsFilter> bean = new FilterRegistrationBean<>(new CorsFilter(source));
        bean.setOrder(0);
        return bean;
    }
}
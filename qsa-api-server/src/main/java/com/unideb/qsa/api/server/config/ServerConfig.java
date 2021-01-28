package com.unideb.qsa.api.server.config;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.info.BuildProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.Resource;
import org.springframework.lang.NonNull;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.maxmind.db.Reader;
import com.maxmind.geoip2.DatabaseReader;

import io.micrometer.core.instrument.Meter;
import io.micrometer.core.instrument.config.MeterFilter;
import io.micrometer.core.instrument.config.MeterFilterReply;

/**
 * Configuration for the server.
 */
@Configuration
public class ServerConfig {

    private static final Logger LOG = LoggerFactory.getLogger(ServerConfig.class);
    private static final String GEO_CONFIGURATION_LOG = "Geo configuration [enabled={}],[path={}]";

    @Value("${management.metrics.enabled}")
    private String[] enabledMetrics;

    @Autowired
    private BuildProperties buildProperties;
    @Value("classpath:schema.graphqls")
    private Resource resource;
    @Value("${geo.enabled}")
    private Boolean metricsGeoEnabled;
    @Value("${geo.path}")
    private String path;

    @PostConstruct
    public void setup() {
        String applicationVersion = buildProperties.getVersion();
        MDC.put("version", applicationVersion);
    }

    @Bean
    public MeterFilter meterFilter() {
        return new MeterFilter() {
            @Override
            @NonNull
            public MeterFilterReply accept(@NonNull Meter.Id id) {
                return Optional.of(List.of(enabledMetrics).contains(id.getName()))
                               .filter(isEnabled -> isEnabled)
                               .map(isEnabled -> MeterFilterReply.ACCEPT)
                               .orElse(MeterFilterReply.DENY);
            }
        };
    }

    @Bean
    @Profile(value = "dev | lab")
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(@NonNull CorsRegistry registry) {
                registry.addMapping("/graphql").allowedOrigins("http://localhost:4200");
            }
        };
    }

    @Bean
    @ConditionalOnProperty(value = "geo.enabled", havingValue = "true")
    public DatabaseReader maxmindDatabaseReader() throws IOException {
        LOG.info(GEO_CONFIGURATION_LOG, metricsGeoEnabled, path);
        return new DatabaseReader.Builder(new File(path))
                .fileMode(Reader.FileMode.MEMORY)
                .build();
    }

}

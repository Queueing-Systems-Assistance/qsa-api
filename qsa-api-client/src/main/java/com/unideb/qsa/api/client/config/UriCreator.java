package com.unideb.qsa.api.client.config;

import java.net.URI;

import org.springframework.web.util.UriComponentsBuilder;

/**
 * Builder for {@link URI}s.
 */
public class UriCreator {

    private final String scheme;
    private final String host;
    private final String port;
    private final String endpoint;

    public UriCreator(String scheme, String host, String port, String endpoint) {
        this.scheme = scheme;
        this.host = host;
        this.port = port;
        this.endpoint = endpoint;
    }

    /**
     * Creates an {@link URI} based on the configuration.
     * @return an assembled and ready to call {@link URI}
     */
    public URI createUri() {
        return UriComponentsBuilder.newInstance()
                                   .scheme(scheme)
                                   .host(host)
                                   .port(port)
                                   .path(endpoint)
                                   .build()
                                   .encode()
                                   .toUri();
    }

    /**
     * Creates an {@link URI} based on the configuration and the given system id.
     * @param systemId system id
     * @return an assembled and ready to call {@link URI}
     */
    public URI createUri(String systemId) {
        return UriComponentsBuilder.newInstance()
                                   .scheme(scheme)
                                   .host(host)
                                   .port(port)
                                   .path(String.format(endpoint, systemId))
                                   .build()
                                   .encode()
                                   .toUri();
    }

    /**
     * Creates an {@link URI} based on the configuration, the given system id and the output feature.
     * @param systemId  system id
     * @param featureId output feature id
     * @return an assembled and ready to call {@link URI}
     */
    public URI createUri(String systemId, String featureId) {
        return UriComponentsBuilder.newInstance()
                                   .scheme(scheme)
                                   .host(host)
                                   .port(port)
                                   .path(String.format(endpoint, systemId, featureId))
                                   .build()
                                   .encode()
                                   .toUri();
    }
}

package com.unideb.qsa.api.client.config.client;

import java.net.URI;
import java.util.concurrent.CompletableFuture;

import org.springframework.http.HttpHeaders;

import com.unideb.qsa.api.client.config.gateway.Gateway;
import com.unideb.qsa.api.client.config.header.factory.HeaderFactory;
import com.unideb.qsa.api.client.config.request.QsaApiRequest;

/**
 * Generic class for implementations of common HTTP POST client tasks.
 * @param <RESPONSE> Type of the response body.
 */
public class GenericClient<RESPONSE> {

    private final Gateway<RESPONSE> gateway;
    private final HeaderFactory headerFactory;

    public GenericClient(Gateway<RESPONSE> gateway, HeaderFactory headerFactory) {
        this.gateway = gateway;
        this.headerFactory = headerFactory;
    }

    /**
     * Calls the gateway and creates a future response based on the request body.
     * @param requestBody the request body
     * @return the response wrapped into a {@link CompletableFuture} object
     */
    public <REQUEST> RESPONSE call(REQUEST requestBody, URI uri) {
        QsaApiRequest<REQUEST> request = createRequestEntity(requestBody, uri);
        return gateway.call(request);
    }

    private <REQUEST> QsaApiRequest<REQUEST> createRequestEntity(REQUEST requestBody, URI uri) {
        HttpHeaders httpHeaders = new HttpHeaders(headerFactory.createHeaders());
        return new QsaApiRequest<>(uri, requestBody, httpHeaders);
    }
}

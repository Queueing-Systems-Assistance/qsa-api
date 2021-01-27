package com.unideb.qsa.api.client.config.gateway;


import com.unideb.qsa.api.client.config.request.QsaApiRequest;

/**
 * Interface to wrap the actual HTTP client calls.
 * @param <RESPONSE> Type of the response body.
 */
public interface Gateway<RESPONSE> {

    /**
     * Calls the underlying service with the given request.
     * @param request the HTTP request entity
     * @return the response
     */
    <REQUEST> RESPONSE call(QsaApiRequest<REQUEST> request);

}


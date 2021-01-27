package com.unideb.qsa.api.client.config.request;

/**
 * Interface to create a string from a {@link QsaApiRequest} object, mainly for displaying reasons.
 */
@FunctionalInterface
public interface QsaApiRequestComponentResolver {

    /**
     * Resolves a string representation of a request entity.
     * @param requestQsaApiRequest the request entity
     * @return type of the request's body
     */
    String resolve(QsaApiRequest<?> requestQsaApiRequest);
}

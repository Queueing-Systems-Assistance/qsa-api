package com.unideb.qsa.api.client.config.request;

import java.net.URI;

import org.springframework.http.HttpHeaders;

/**
 * Holder for an outgoing request.
 */
public class QsaApiRequest<REQUEST> {

    private final URI uri;
    private final REQUEST request;
    private final HttpHeaders headers;

    public QsaApiRequest(URI uri, REQUEST request, HttpHeaders headers) {
        this.uri = uri;
        this.request = request;
        this.headers = headers;
    }

    public HttpHeaders getHeaders() {
        return headers;
    }

    public REQUEST getRequest() {
        return request;
    }

    public URI getUri() {
        return uri;
    }

}

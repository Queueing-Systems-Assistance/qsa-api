package com.unideb.qsa.api.client.config.request;

import java.util.function.Function;

/**
 * Enum class for identifying specific {@link QsaApiRequest} contents.
 */
public enum QsaApiRequestComponent {
    URL("url", QsaApiRequest::getUri),
    BODY("body", QsaApiRequest::getRequest),
    HEADERS("headers", QsaApiRequest::getHeaders);

    private final String name;
    private final Function<QsaApiRequest<?>, ?> getter;

    QsaApiRequestComponent(String name, Function<QsaApiRequest<?>, ?> getter) {
        this.name = name;
        this.getter = getter;
    }

    public String getName() {
        return name;
    }

    public Function<QsaApiRequest<?>, ?> getter() {
        return getter;
    }
}

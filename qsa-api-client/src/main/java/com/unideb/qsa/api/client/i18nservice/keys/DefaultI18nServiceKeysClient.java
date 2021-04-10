package com.unideb.qsa.api.client.i18nservice.keys;

import java.util.List;

import com.unideb.qsa.api.client.config.UriCreator;
import com.unideb.qsa.api.client.config.client.GenericClient;
import com.unideb.qsa.api.domain.i18nservice.response.I18nElementResponse;

/**
 * Default implementation of resolving i18n keys from i18n-service.
 */
public class DefaultI18nServiceKeysClient implements I18nServiceKeysClient {

    private final UriCreator uriCreator;
    private final GenericClient<List<I18nElementResponse>> genericClient;

    public DefaultI18nServiceKeysClient(GenericClient<List<I18nElementResponse>> genericClient, UriCreator uriCreator) {
        this.uriCreator = uriCreator;
        this.genericClient = genericClient;
    }

    @Override
    public List<I18nElementResponse> getI18nElements(List<String> keys) {
        return genericClient.call(keys, uriCreator.createUri());
    }
}

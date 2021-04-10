package com.unideb.qsa.api.client.i18nservice.keys;

import java.util.List;

import com.unideb.qsa.api.domain.i18nservice.response.I18nElementResponse;

/**
 * Interface for resolving i18n keys from i18n-service.
 */
public interface I18nServiceKeysClient {

    /**
     * Resolves i18n elements based on the given i18n keys.
     * @param keys i18n keys
     * @return resolved i18n values, with all the locales
     */
    List<I18nElementResponse> getI18nElements(List<String> keys);

}

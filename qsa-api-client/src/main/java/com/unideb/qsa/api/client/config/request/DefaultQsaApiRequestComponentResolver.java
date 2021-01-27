package com.unideb.qsa.api.client.config.request;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Resolves a request's components in a predefined format, eg., "[url=http://url, headers=[Accept-Language:"en_US"]]".
 */
public class DefaultQsaApiRequestComponentResolver implements QsaApiRequestComponentResolver {

    private static final String KEY_VALUE_DELIMITER = "=";
    private static final String COMPONENTS_DELIMITER = ", ";
    private static final String COMPONENT_PREFIX = "[";
    private static final String COMPONENT_SUFFIX = "]";
    private final List<QsaApiRequestComponent> requestComponents;

    public DefaultQsaApiRequestComponentResolver(List<QsaApiRequestComponent> requestComponents) {
        this.requestComponents = requestComponents;
    }

    @Override
    public String resolve(QsaApiRequest<?> apiRequest) {
        return requestComponents.stream()
                                .map(component -> resolvePart(apiRequest, component))
                                .collect(Collectors.joining(COMPONENTS_DELIMITER, COMPONENT_PREFIX, COMPONENT_SUFFIX));
    }

    private String resolvePart(QsaApiRequest<?> request, QsaApiRequestComponent component) {
        Object value = component.getter().apply(request);
        return String.join(KEY_VALUE_DELIMITER, component.getName(), String.valueOf(value));
    }
}

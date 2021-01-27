package com.unideb.qsa.api.client.config.header.factory;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

/**
 * Aggregator class for request header factories.
 */
@Component
public class CompositeRequestHeadersFactory implements HeaderFactory {

    private final List<HeaderFactory> requestHeadersFactories;

    public CompositeRequestHeadersFactory(List<HeaderFactory> requestHeadersFactories) {
        this.requestHeadersFactories = requestHeadersFactories;
    }

    @Override
    public MultiValueMap<String, String> createHeaders() {
        return requestHeadersFactories.stream()
                                      .map(HeaderFactory::createHeaders)
                                      .map(Map::entrySet)
                                      .flatMap(Set::stream)
                                      .collect(Collectors.toMap(
                                              Map.Entry::getKey,
                                              Map.Entry::getValue,
                                              this::mergeForSameKey,
                                              LinkedMultiValueMap::new
                                      ));
    }

    private List<String> mergeForSameKey(List<String> values, List<String> conflictingValues) {
        var combinedValues = new HashSet<>(values);
        combinedValues.addAll(conflictingValues);
        return new LinkedList<>(combinedValues);
    }
}

package com.unideb.qsa.api.implementation.updater;

import java.util.Locale;

import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

import graphql.servlet.GraphQLContext;

/**
 * Updater to update locale when a GraphQL requests comes.
 */
@Component
public class LocaleUpdater {

    private static final String DEFAULT_LOCALE = "en_US";

    /**
     * Updates locale based on the current GraphQL context.
     * @param graphQLContext context
     */
    public void updateLocale(GraphQLContext graphQLContext) {
        LocaleContextHolder.setLocale(resolveLocale(graphQLContext));
    }

    private Locale resolveLocale(GraphQLContext graphQLContext) {
        return graphQLContext.getHttpServletRequest()
                             .map(request -> request.getHeader(HttpHeaders.ACCEPT_LANGUAGE))
                             .map(Locale::new)
                             .orElse(new Locale(DEFAULT_LOCALE));
    }

}

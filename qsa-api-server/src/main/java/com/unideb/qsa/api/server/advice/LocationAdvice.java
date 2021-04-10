package com.unideb.qsa.api.server.advice;

import java.io.IOException;
import java.net.InetAddress;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.unideb.qsa.api.implementation.geo.LocationResolver;

/**
 * Advice to resolve & report the request location based on its IP address.
 */
@Aspect
@Component
@Profile("!dev")
public class LocationAdvice {

    private static final Logger LOG = LoggerFactory.getLogger(LocationAdvice.class);
    private static final String HEADER = "X-Metrics-IP";
    private static final String LOG_MESSAGE = "Request location resolved";
    private static final String COUNTRY_ID = "countryId";
    private static final String ERROR_PARSING_IP_ADDRESS = "Error parsing IP address";

    @Autowired
    private LocationResolver locationResolver;

    /**
     * Advices the system element calculation.
     * We need to advice only this part of the code, so that when requesting i18n elements we won't report the country.
     */
    @Before("execution(* com.unideb.qsa.api.implementation.graphql.SystemElementsGraphQLFetcher.getSystemElements(*))")
    public void adviceSystemElements() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        String ipAddress = request.getHeader(HEADER);
        reportLocation(ipAddress);
    }

    private void reportLocation(String ipAddress) {
        try {
            String country = resolveCountry(ipAddress);
            MDC.put(COUNTRY_ID, country);
            LOG.info(LOG_MESSAGE);
            MDC.remove(COUNTRY_ID);
        } catch (IOException exception) {
            LOG.warn(ERROR_PARSING_IP_ADDRESS, exception);
        }
    }

    private String resolveCountry(String ipAddress) throws IOException {
        InetAddress inetAddress = InetAddress.getByName(ipAddress);
        return locationResolver.resolveCountryIsoCode(inetAddress);
    }

}

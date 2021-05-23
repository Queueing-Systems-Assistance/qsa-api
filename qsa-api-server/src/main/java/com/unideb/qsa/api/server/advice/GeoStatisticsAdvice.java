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

import com.unideb.qsa.api.implementation.geo.CountryResolver;
import com.unideb.qsa.api.implementation.geo.LocationResolver;

/**
 * Advice to resolve & report the request location based on its IP address.
 */
@Aspect
@Component
@Profile("!dev")
public class GeoStatisticsAdvice {

    private static final Logger LOG = LoggerFactory.getLogger(GeoStatisticsAdvice.class);
    private static final String HEADER = "X-Metrics-IP";
    private static final String REQUEST_COUNTRY_RESOLVED = "Request country resolved";
    private static final String REQUEST_LOCATION_RESOLVED = "Request location resolved";
    private static final String COUNTRY_ID = "countryId";
    private static final String LOCATION = "location";
    private static final String ERROR_PARSING_IP_ADDRESS = "Error parsing IP address";

    @Autowired
    private CountryResolver countryResolver;
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
            InetAddress inetAddress = InetAddress.getByName(ipAddress);
            reportCountry(inetAddress);
            reportLocation(inetAddress);
        } catch (IOException exception) {
            LOG.warn(ERROR_PARSING_IP_ADDRESS, exception);
        }
    }

    private void reportCountry(InetAddress inetAddress) {
        String country = countryResolver.resolveCountryIsoCode(inetAddress);
        MDC.put(COUNTRY_ID, country);
        LOG.info(REQUEST_COUNTRY_RESOLVED);
        MDC.remove(COUNTRY_ID);
    }

    private void reportLocation(InetAddress inetAddress) {
        String country = locationResolver.resolveLocationGeoPoint(inetAddress);
        MDC.put(LOCATION, country);
        LOG.info(REQUEST_LOCATION_RESOLVED);
        MDC.remove(LOCATION);
    }
}

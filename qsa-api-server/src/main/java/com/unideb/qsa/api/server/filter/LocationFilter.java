package com.unideb.qsa.api.server.filter;

import java.io.IOException;
import java.net.InetAddress;
import java.util.Optional;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.core.annotation.Order;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.filter.OncePerRequestFilter;

import com.unideb.qsa.api.implementation.geo.LocationResolver;

/**
 * Filter for reporting geolocation.
 */
@Component
@Order(value = 3)
@ConditionalOnProperty(value = "geo.enabled", havingValue = "true")
public class LocationFilter extends OncePerRequestFilter {

    private static final Logger LOG = LoggerFactory.getLogger(LocationFilter.class);
    private static final String HEADER = "X-Metrics-IP";
    private static final String LOG_MESSAGE = "Request location resolved";
    private static final String COUNTRY_ID = "countryId";
    private static final String ERROR_PARSING_IP_ADDRESS = "Error parsing IP address";
    private static final String ACTUATOR_ENDPOINT = "actuator";

    @Autowired
    private LocationResolver locationResolver;

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain) throws ServletException, IOException {
        try {
            reportLocation();
        } catch (IOException exception) {
            LOG.warn(ERROR_PARSING_IP_ADDRESS, exception);
        } finally {
            super.doFilter(request, response, filterChain);
        }
    }


    private void reportLocation() throws IOException {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        String ipAddress = request.getHeader(HEADER);
        if (!request.getServletPath().contains(ACTUATOR_ENDPOINT)) {
            resolveCountry(ipAddress).ifPresent(this::reportCountry);
        }
    }

    private Optional<String> resolveCountry(String ipAddress) throws IOException {
        InetAddress inetAddress = InetAddress.getByName(ipAddress);
        return locationResolver.resolveCountryIsoCode(inetAddress);
    }

    private void reportCountry(String country) {
        MDC.put(COUNTRY_ID, country);
        LOG.info(LOG_MESSAGE);
        MDC.remove(COUNTRY_ID);
    }
}

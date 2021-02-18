package com.unideb.qsa.api.implementation.geo;

import java.io.IOException;
import java.net.InetAddress;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.maxmind.geoip2.DatabaseReader;
import com.maxmind.geoip2.exception.GeoIp2Exception;
import com.maxmind.geoip2.model.CountryResponse;
import com.maxmind.geoip2.record.Country;

/**
 * Resolver for geography location.
 */
@Component
public class LocationResolver {

    private static final Logger LOG = LoggerFactory.getLogger(LocationResolver.class);
    private static final String FAILED_TO_RESOLVE_COUNTRY = "Failed to resolve country";

    @Autowired(required = false)
    private DatabaseReader databaseReader;
    @Value("${geo.filtered-addresses}")
    private List<String> filteredAddresses;

    /**
     * Resolves country code based on IP address.
     * @param ipAddress IP address
     * @return resolved country code (eg. HU, GB, etc.)
     */
    public String resolveCountryIsoCode(InetAddress ipAddress) {
        String result = "unknown";
        if (!filteredAddresses.contains(ipAddress.getHostAddress())) {
            try {
                result = resolveCountry(ipAddress).getIsoCode();
            } catch (RuntimeException | IOException | GeoIp2Exception e) {
                LOG.error(FAILED_TO_RESOLVE_COUNTRY, e);
            }
        }
        return result;
    }

    private Country resolveCountry(InetAddress ipAddress) throws IOException, GeoIp2Exception {
        CountryResponse response = databaseReader.country(ipAddress);
        return response.getCountry();
    }
}
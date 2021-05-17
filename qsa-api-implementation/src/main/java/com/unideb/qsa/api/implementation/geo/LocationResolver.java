package com.unideb.qsa.api.implementation.geo;

import java.io.IOException;
import java.net.InetAddress;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.maxmind.geoip2.DatabaseReader;
import com.maxmind.geoip2.exception.GeoIp2Exception;
import com.maxmind.geoip2.model.CityResponse;
import com.maxmind.geoip2.record.Location;

/**
 * Resolver for geography location.
 */
@Component
public class LocationResolver {

    private static final Logger LOG = LoggerFactory.getLogger(LocationResolver.class);
    private static final String FAILED_TO_RESOLVE_LOCATION_GEO_POINT = "Failed to resolve location geo point";

    @Autowired(required = false)
    private DatabaseReader databaseReader;

    /**
     * Resolves geo point based on IP address.
     * @param ipAddress IP address
     * @return resolved geo point: "41.12,-71.34"
     */
    public String resolveLocationGeoPoint(InetAddress ipAddress) {
        String result = "";
        try {
            Location location = resolveLocation(ipAddress);
            result = String.format("%s,%s", location.getLatitude(), location.getLongitude());
        } catch (RuntimeException | IOException | GeoIp2Exception e) {
            LOG.error(FAILED_TO_RESOLVE_LOCATION_GEO_POINT, e);
        }
        return result;
    }

    private Location resolveLocation(InetAddress ipAddress) throws IOException, GeoIp2Exception {
        CityResponse response = databaseReader.city(ipAddress);
        return response.getLocation();
    }
}

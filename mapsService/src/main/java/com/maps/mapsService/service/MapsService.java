package com.maps.mapsService.service;

import com.maps.mapsService.model.Bank;
import com.maps.mapsService.model.Coordinates;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
public class MapsService {

    @Value("${google.maps.api.key}")
    private String apiKey;
    @Value("${google.maps.geocode.url}")
    private String geocodeUrl;
    @Value("${google.maps.places.url}")
    private String placesUrl;

    private final RestTemplate restTemplate = new RestTemplate();

    public Coordinates getCoordinatesFromZip(String zipCode) {
        String url = String.format("%s?address=%s&key=%s", geocodeUrl, zipCode, apiKey);
        Map<?, ?> response = restTemplate.getForObject(url, Map.class);
        if (response == null || !response.containsKey("results")) return null;

        List<?> results = (List<?>) response.get("results");
        if (results.isEmpty()) return null;

        Map<?, ?> location = (Map<?, ?>) ((Map<?, ?>) ((Map<?, ?>) results.get(0)).get("geometry")).get("location");
        return new Coordinates((Double) location.get("lat"), (Double) location.get("lng"));
    }

    public List<Bank> findNearbyBanks(double lat, double lng, int radius) {
        String url = String.format("%s?location=%f,%f&radius=%d&type=bank&key=%s",
                placesUrl, lat, lng, radius, apiKey);

        Map<?, ?> response = restTemplate.getForObject(url, Map.class);
        List<Bank> banks = new ArrayList<>();

        if (response != null && response.containsKey("results")) {
            List<?> results = (List<?>) response.get("results");
            for (Object obj : results) {
                Map<?, ?> result = (Map<?, ?>) obj;
                String name = (String) result.get("name");
                String address = (String) result.get("vicinity");
                banks.add(new Bank(name, address));
            }
        }

        return banks;
    }
}


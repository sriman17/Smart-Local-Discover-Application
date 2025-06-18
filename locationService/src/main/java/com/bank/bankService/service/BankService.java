package com.bank.bankService.service;

import com.bank.bankService.model.Bank;
import com.bank.bankService.model.Coordinates;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class BankService {

    @Value("${maps.service.base.url}")
    private String mapsServiceUrl;

    private final RestTemplate restTemplate = new RestTemplate();

    public List<Bank> getBanksNearZip(String zipCode) {
        String coordUrl = mapsServiceUrl + "/coordinates?zip=" + zipCode;
        Coordinates coords = restTemplate.getForObject(coordUrl, Coordinates.class);

        if (coords == null) {
            throw new RuntimeException("Invalid ZIP code or coordinates not found.");
        }

        String placesUrl = String.format("%s/places?lat=%f&lng=%f&radius=16093",
                mapsServiceUrl, coords.getLat(), coords.getLng());

        Bank[] banks = restTemplate.getForObject(placesUrl, Bank[].class);
        return Arrays.asList(banks);
    }
}


package com.maps.mapsService.controller;


import com.maps.mapsService.model.Bank;
import com.maps.mapsService.model.Coordinates;
import com.maps.mapsService.service.MapsService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/maps")
public class MapsController {

    private final MapsService mapsService;

    public MapsController(MapsService mapsService) {
        this.mapsService = mapsService;
    }

    @GetMapping("/coordinates")
    public Coordinates getCoordinates(@RequestParam String zip) {
        return mapsService.getCoordinatesFromZip(zip);
    }

    @GetMapping("/places")
    public List<Bank> getNearbyBanks(@RequestParam double lat,
                                     @RequestParam double lng,
                                     @RequestParam(defaultValue = "16093") int radius) {
        return mapsService.findNearbyBanks(lat, lng, radius);
    }
}


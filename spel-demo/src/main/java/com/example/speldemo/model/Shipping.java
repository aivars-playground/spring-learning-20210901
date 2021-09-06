package com.example.speldemo.model;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component("shipping")
public class Shipping {
    private Map<String, List<City>> locationsByCountry = new HashMap<>();
    private Map<String, List<City>> costsByCountry = new HashMap<>();

    public Shipping() {

        List<City> usCities = new ArrayList<>();
        usCities.add(new City("New York", 1F, false));
        usCities.add(new City("Washington", 2F, true));

        List<City> ukCities = new ArrayList<>();
        ukCities.add(new City("Newcastle", 3F, false));
        ukCities.add(new City("London", 4F,true));

        locationsByCountry.put("US",usCities);
        locationsByCountry.put("UK",ukCities);

        costsByCountry.put("US",usCities);
        costsByCountry.put("UK",ukCities);

    }

    public Map<String, List<City>> getLocationsByCountry() {
        return locationsByCountry;
    }

    public void setLocationsByCountry(Map<String, List<City>> locationsByCountry) {
        this.locationsByCountry = locationsByCountry;
    }

    public Map<String, List<City>> getCostsByCountry() {
        return costsByCountry;
    }

    public void setCostsByCountry(Map<String, List<City>> costsByCountry) {
        this.costsByCountry = costsByCountry;
    }
}

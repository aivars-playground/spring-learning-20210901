package com.example.speldemo.model;

import org.springframework.stereotype.Component;

@Component("city")
public class City {
    private String name;
    private Float shippingCost;
    private Boolean isCapital;

    public City() {

    }

    public City(String name,Float shippingCost, Boolean isCapital) {
        this.name = name;
        this.isCapital = isCapital;
        this.shippingCost = shippingCost;
    }

    public Float getShippingCost() {
        return shippingCost;
    }

    public void setShippingCost(Float shippingCost) {
        this.shippingCost = shippingCost;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getIsCapital() {
        return isCapital;
    }

    public void setIsCapital(Boolean capital) {
        isCapital = capital;
    }
}

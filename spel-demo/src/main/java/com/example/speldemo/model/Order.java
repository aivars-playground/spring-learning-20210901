package com.example.speldemo.model;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("order")
public class Order {

    @Value("#{9000}")
    private double amount;

    @Value("#{T(java.text.NumberFormat).getCurrencyInstance(T(java.util.Locale).getDefault()).format(order.amount)}")
    private String formattedAmount;

    //5% discount on a larger order
    @Value("#{order.amount >= 1000 ? order.amount * 5 / 100 : 0 }")
    private double discount;

    //faster delivery within USA
    @Value("#{user.country == 'US' ? 3 : 14}")
    private int daysToDeliver;

    @Value("#{user.country}")
    private String deliveryAddress;

    @Value("#{shipping.locationsByCountry[user.country]}")
    private List<City> shippingLocations;

    @Value("#{order.shippingLocations.?[isCapital]}")
    private List<City> shippingCapitals;

    public List<City> getShippingCapitals() {
        return shippingCapitals;
    }

    public void setShippingCapitals(List<City> shippingCapitals) {
        this.shippingCapitals = shippingCapitals;
    }

    public List<City> getShippingLocations() {
        return shippingLocations;
    }

    public void setShippingLocations(List<City> shippingLocations) {
        this.shippingLocations = shippingLocations;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public String getFormattedAmount() {
        return formattedAmount;
    }

    public void setFormattedAmount(String formattedAmount) {
        this.formattedAmount = formattedAmount;
    }

    public int getDaysToDeliver() {
        return daysToDeliver;
    }

    public void setDaysToDeliver(int daysToDeliver) {
        this.daysToDeliver = daysToDeliver;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }
}

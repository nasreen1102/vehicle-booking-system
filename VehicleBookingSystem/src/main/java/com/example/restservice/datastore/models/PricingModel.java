package com.example.restservice.datastore.models;

public class PricingModel {
    private PricingKey pricingKey;
    private Double price;


    public PricingModel(PricingKey pricingKey, Double price) {
        this.pricingKey = pricingKey;
        this.price = price;
    }

    public PricingKey getPricingKey() {
        return pricingKey;
    }

    public void setPricingKey(PricingKey pricingKey) {
        this.pricingKey = pricingKey;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Pricing{" +
                "pricingKey=" + pricingKey +
                ", price=" + price +
                '}';
    }
}

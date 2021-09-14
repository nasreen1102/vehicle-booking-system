package com.example.restservice.controller.entity;

public class RentalPrice {
    private String vehicleType;
    private Long branchId;
    private Double pricePerHour;

    public RentalPrice() {
    }

    public RentalPrice(String vehicleType, Long branchId, Double pricePerHour) {
        this.vehicleType = vehicleType;
        this.branchId = branchId;
        this.pricePerHour = pricePerHour;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public Long getBranchId() {
        return branchId;
    }

    public void setBranchId(Long branchId) {
        this.branchId = branchId;
    }

    public Double getPricePerHour() {
        return pricePerHour;
    }

    public void setPricePerHour(Double pricePerHour) {
        this.pricePerHour = pricePerHour;
    }
}

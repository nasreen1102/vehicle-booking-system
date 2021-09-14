package com.example.restservice.controller.entity;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class BookingDetails {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy hh:mm a");
    private String vehicleType;
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    public BookingDetails() {
    }

    public BookingDetails(String vehicleType, LocalDateTime startTime, LocalDateTime endTime) {
        this.vehicleType = vehicleType;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = LocalDateTime.parse(startTime, formatter);
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = LocalDateTime.parse(endTime, formatter);
    }
}

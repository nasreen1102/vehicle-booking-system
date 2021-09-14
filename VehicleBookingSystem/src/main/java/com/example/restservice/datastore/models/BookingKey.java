package com.example.restservice.datastore.models;

import java.time.LocalDateTime;
import java.util.Objects;

public class BookingKey {
    private String vehicleNumber;
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    public BookingKey(String vehicleNumber, LocalDateTime startTime, LocalDateTime endTime) {
        this.vehicleNumber = vehicleNumber;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public void setVehicleNumber(String vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookingKey that = (BookingKey) o;
        return Objects.equals(vehicleNumber, that.vehicleNumber) && Objects.equals(startTime, that.startTime) && Objects.equals(endTime, that.endTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(vehicleNumber, startTime, endTime);
    }

    @Override
    public String toString() {
        return "BookingKey{" +
                "vehicleNumber='" + vehicleNumber + '\'' +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                '}';
    }
}

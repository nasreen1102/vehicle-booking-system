package com.example.restservice.datastore.models;

import com.example.restservice.enums.VehicleTypeEnum;

public class VehicleModel {
    private String vehicleNumber;
    private VehicleTypeEnum vehicleType;
    private Branch branch;

    public VehicleModel(String vehicleNumber, VehicleTypeEnum vehicleType, Branch branch) {
        this.vehicleNumber = vehicleNumber;
        this.vehicleType = vehicleType;
        this.branch = branch;
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public void setVehicleNumber(String vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
    }

    public VehicleTypeEnum getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(VehicleTypeEnum vehicleType) {
        this.vehicleType = vehicleType;
    }

    public Branch getBranch() {
        return branch;
    }

    public void setBranch(Branch branch) {
        this.branch = branch;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "vehicleNumber='" + vehicleNumber + '\'' +
                ", vehicleType=" + vehicleType +
                ", branch=" + branch +
                '}';
    }
}

package com.example.restservice.controller.entity;

import com.example.restservice.datastore.models.Branch;
import com.example.restservice.datastore.models.VehicleModel;
import com.example.restservice.enums.VehicleTypeEnum;

import java.util.Optional;

public class Vehicle {
    private String vehicleNumber;
    private String vehicleType;
    private Long branchId;

    public Vehicle() {
    }

    public Vehicle(String vehicleNumber, String vehicleType, Long branchId) {
        this.vehicleNumber = vehicleNumber;
        this.vehicleType = vehicleType;
        this.branchId = branchId;
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public void setVehicleNumber(String vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
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

    public VehicleModel toModel(Branch branch) throws Exception {
        return new VehicleModel(this.vehicleNumber, VehicleTypeEnum.get(vehicleType), branch);
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "vehicleNumber='" + vehicleNumber + '\'' +
                ", vehicleType='" + vehicleType + '\'' +
                ", branchId=" + branchId +
                '}';
    }
}

package com.example.restservice.datastore.models;

import com.example.restservice.enums.VehicleTypeEnum;

import java.util.Objects;

public class PricingKey {
    private VehicleTypeEnum vehicleTypeEnum;
    private Branch branch;

    public PricingKey(VehicleTypeEnum vehicleTypeEnum, Branch branch) {
        this.vehicleTypeEnum = vehicleTypeEnum;
        this.branch = branch;
    }

    public VehicleTypeEnum getVehicleTypeEnum() {
        return vehicleTypeEnum;
    }

    public void setVehicleTypeEnum(VehicleTypeEnum vehicleTypeEnum) {
        this.vehicleTypeEnum = vehicleTypeEnum;
    }

    public Branch getBranch() {
        return branch;
    }

    public void setBranch(Branch branch) {
        this.branch = branch;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PricingKey that = (PricingKey) o;
        return vehicleTypeEnum == that.vehicleTypeEnum && Objects.equals(branch, that.branch);
    }

    @Override
    public int hashCode() {
        return Objects.hash(vehicleTypeEnum, branch);
    }

    @Override
    public String toString() {
        return "{" +
                "vehicleTypeEnum=" + vehicleTypeEnum +
                ", branch=" + branch +
                '}';
    }
}

package com.example.restservice.enums;

import java.util.Objects;
import java.util.Optional;

public enum VehicleTypeEnum {
    HATCHBACK, SEDAN, SUV;

    public static VehicleTypeEnum get(String vehicleType) throws Exception {

        for (VehicleTypeEnum vType: values()) {
          if(Objects.nonNull(vehicleType) && vType.name().equalsIgnoreCase(vehicleType)){
              return vType;
          }
        }

        throw new Exception("Invalid vehicleType");
    }
}

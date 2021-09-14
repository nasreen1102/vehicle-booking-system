package com.example.restservice.service;

import com.example.restservice.datastore.models.PricingModel;
import com.example.restservice.datastore.models.VehicleModel;
import org.apache.commons.lang3.tuple.ImmutablePair;

import java.util.Optional;
import java.util.Set;

public interface SelectionStrategy {
    Optional<ImmutablePair<VehicleModel, PricingModel>> get(Set<VehicleModel> vehicles);
}

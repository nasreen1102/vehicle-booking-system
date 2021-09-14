package com.example.restservice.service;

import com.example.restservice.datastore.VehicleDao;
import com.example.restservice.datastore.models.PricingKey;
import com.example.restservice.datastore.models.PricingModel;
import com.example.restservice.datastore.models.VehicleModel;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

@Service
public class PricingStrategy implements SelectionStrategy {

    @Autowired
    private VehicleDao vehicleDao;

    @Override
    public Optional<ImmutablePair<VehicleModel, PricingModel>> get(Set<VehicleModel> vehicles) {

        final Comparator<ImmutablePair<VehicleModel, PricingModel>> pricingComparator =
                new Comparator<ImmutablePair<VehicleModel, PricingModel>>() {
                    @Override
                    public int compare(ImmutablePair<VehicleModel, PricingModel> o1, ImmutablePair<VehicleModel, PricingModel> o2) {
                        return o1.getRight().getPrice().compareTo(o2.getRight().getPrice());
                    }
                };
        final Map<PricingKey, PricingModel> pricingDataMap = vehicleDao.getPricingDataMap();
        final Optional<ImmutablePair<VehicleModel, PricingModel>> min = vehicles.stream().map(vehicle -> {
            final PricingModel pricingModel = pricingDataMap.get(new PricingKey(vehicle.getVehicleType(), vehicle.getBranch()));
            return ImmutablePair.of(vehicle, pricingModel);
        }).min(pricingComparator);

        return min;
    }

    public PricingStrategy() {
    }

    public PricingStrategy(VehicleDao vehicleDao) {
        this.vehicleDao = vehicleDao;
    }
}

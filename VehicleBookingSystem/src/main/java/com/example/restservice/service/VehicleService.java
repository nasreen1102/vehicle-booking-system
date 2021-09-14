package com.example.restservice.service;

import com.example.restservice.controller.entity.BookingDetails;
import com.example.restservice.controller.entity.Vehicle;
import com.example.restservice.datastore.VehicleDao;
import com.example.restservice.datastore.models.PricingModel;
import com.example.restservice.datastore.models.VehicleModel;
import com.example.restservice.enums.VehicleTypeEnum;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class VehicleService {

    @Autowired
    private VehicleDao vehicleDao;

    public void addBranch(String branchName) {
        vehicleDao.add(branchName);
    }

    public void addVehicle(Vehicle vehicle) throws Exception {
        vehicleDao.add(vehicle);
    }

    public Optional<VehicleModel> bookVehicle(BookingDetails bookingDetails) throws Exception {
        try {
            final VehicleTypeEnum vehicleTypeEnum = VehicleTypeEnum.get(bookingDetails.getVehicleType());
            //1. Get all vehicles by given type
            Set<VehicleModel> vehicles = getByVehicleType(vehicleTypeEnum);
            //2. get all available vehicles between start and end time
            Set<VehicleModel> availableVehicles = availableVehicles(vehicles, bookingDetails.getStartTime(), bookingDetails.getEndTime());
            //3. select the strategy and get the resultant vehicle and priceModel
            SelectionStrategy selectionStrategy = SelectionStrategyFactory.get(StrategyTypeEnum.PRICE);
            final Optional<ImmutablePair<VehicleModel, PricingModel>> vehicleAndPricingModel =
                    selectionStrategy.get(availableVehicles);

            if (vehicleAndPricingModel.isPresent()) {
                Double bookingFare = calculateFare(vehicleAndPricingModel.get().getRight(), bookingDetails.getStartTime(),
                        bookingDetails.getEndTime());
                final VehicleModel vehicleModel = vehicleAndPricingModel.get().getLeft();
                vehicleDao.bookVehicle(bookingDetails, bookingFare, vehicleModel);
                return Optional.of(vehicleModel);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        return Optional.empty();
    }

    private Double calculateFare(PricingModel pricingModel, LocalDateTime startTime, LocalDateTime endTime) {
        final Double pricePerHour = pricingModel.getPrice();
        final long totalHours = LocalDateTime.from(startTime).until(endTime, ChronoUnit.HOURS);
        return Math.round(pricePerHour * totalHours * 100) / 100.0; // rounding to two decimals
    }

    private Set<VehicleModel> availableVehicles(Set<VehicleModel> vehicles, LocalDateTime startTime, LocalDateTime endTime) {
        return vehicleDao.filterByAvailability(vehicles, startTime, endTime);
    }

    public Set<VehicleModel> getByVehicleType(VehicleTypeEnum vehicleTypeEnum) {
        return vehicleDao.getVehicleDataMap().values().stream().filter(v-> v.getVehicleType() == vehicleTypeEnum).collect(Collectors.toSet());
    }

    public VehicleService(VehicleDao vehicleDao) {
        this.vehicleDao = vehicleDao;
    }

    public VehicleService() {
    }

    public VehicleDao getVehicleDao() {
        return vehicleDao;
    }
}

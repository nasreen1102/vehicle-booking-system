package com.example.restservice.datastore;

import com.example.restservice.controller.entity.BookingDetails;
import com.example.restservice.controller.entity.RentalPrice;
import com.example.restservice.controller.entity.Vehicle;
import com.example.restservice.datastore.models.*;
import com.example.restservice.enums.VehicleTypeEnum;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Service
public class VehicleDao {

    private final AtomicLong branchCounter = new AtomicLong();
    private Map<Long, Branch> branchDataMap = new HashMap<>();
    private Map<String, VehicleModel> vehicleDataMap = new HashMap<>();
    private Map<PricingKey, PricingModel> pricingDataMap = new HashMap<>();
    private Map<BookingKey, Double> bookingDataMap = new HashMap<>();


    public void add(String branchName) {
        final long id = branchCounter.incrementAndGet();
        branchDataMap.put(id, new Branch(id, branchName));
    }

    public void add(Vehicle vehicle) throws Exception {
        final Branch branch = branchDataMap.get(vehicle.getBranchId());
        try {
            final VehicleModel vehicleModel = vehicle.toModel(branch);
            vehicleDataMap.put(vehicle.getVehicleNumber(), vehicleModel);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    public void setPrice(RentalPrice rentalPrice) throws Exception {
        final Branch branch = branchDataMap.get(rentalPrice.getBranchId());
        try {
            final VehicleTypeEnum vehicleTypeEnum = VehicleTypeEnum.get(rentalPrice.getVehicleType());
            final PricingKey pricingKey = new PricingKey(vehicleTypeEnum, branch);
            pricingDataMap.put(pricingKey, new PricingModel(pricingKey, rentalPrice.getPricePerHour()));
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }

    }

    public Set<VehicleModel> filterByAvailability(Set<VehicleModel> vehicles, LocalDateTime startTime,
                                                  LocalDateTime endTime) {
        return vehicles.stream().filter(v -> isAvailable(v, startTime, endTime)).collect(Collectors.toSet());
    }

    private boolean isAvailable(VehicleModel vehicle, LocalDateTime startTime, LocalDateTime endTime) {
        final Double pricePerHour = bookingDataMap.get(new BookingKey(vehicle.getVehicleNumber(), startTime, endTime));
        // could fall in between start time and endTime
        // can be corrected
        return pricePerHour == null; // vehicle is available if not in booking Map
    }

    public void bookVehicle(BookingDetails bookingDetails, Double totalFare, VehicleModel vehicleModel) {
        final BookingKey bookingKey = new BookingKey(vehicleModel.getVehicleNumber(), bookingDetails.getStartTime(), bookingDetails.getEndTime());
        this.getBookingDataMap().put(bookingKey, totalFare);
    }

    public Map<Long, Branch> getBranchDataMap() {
        return branchDataMap;
    }

    public Map<String, VehicleModel> getVehicleDataMap() {
        return vehicleDataMap;
    }

    public Map<PricingKey, PricingModel> getPricingDataMap() {
        return pricingDataMap;
    }

    public Map<BookingKey, Double> getBookingDataMap() {
        return bookingDataMap;
    }

    public void showBranch() {
        for (Map.Entry<Long, Branch> entry:branchDataMap.entrySet()) {
            System.out.println(entry.getKey()+","+entry.getValue());
        }
    }

    public void showVehicles() {
        for (Map.Entry<String, VehicleModel> entry:vehicleDataMap.entrySet()) {
            System.out.println(entry.getKey()+","+entry.getValue());
        }
    }

    public void showPricing() {
        for (Map.Entry<PricingKey, PricingModel> entry:pricingDataMap.entrySet()) {
            System.out.println(entry.getKey()+","+entry.getValue());
        }
    }

    public void showBookings() {
        for (Map.Entry<BookingKey, Double> entry:bookingDataMap.entrySet()) {
            System.out.println(entry.getKey()+","+entry.getValue());
        }
    }
}

package com.example.restservice.controller;

import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

import com.example.restservice.controller.entity.BookingDetails;
import com.example.restservice.controller.entity.RentalPrice;
import com.example.restservice.controller.entity.Vehicle;
import com.example.restservice.datastore.models.VehicleModel;
import com.example.restservice.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class VehicleController {

	private static final String template = "Hello, %s!";
	private final AtomicLong counter = new AtomicLong();

	@Autowired
	private VehicleService vehicleService;

	@PostMapping("/branch/{name}")
	public String addBranch(@PathVariable(value = "name") String name) {
		vehicleService.addBranch(name);
		return "success";
	}

	@PostMapping("/vehicle")
	public String addVehicle(@RequestBody Vehicle vehicle) {
		try {
			vehicleService.addVehicle(vehicle);
		} catch (Exception e) { //exception could be more specific
			e.printStackTrace();
			return "Invalid vehicle Type - "+ vehicle.getVehicleType();
		}
		return "success";
	}

	@PostMapping("/price")
	public String allocatePrice(@RequestBody RentalPrice rentalPrice) {
		try {
			vehicleService.getVehicleDao().setPrice(rentalPrice);
		} catch (Exception e) {
			e.printStackTrace();
			return "Invalid vehicle Type - "+ rentalPrice.getVehicleType();
		}

		return "success";
	}

	@PostMapping("/book")
	public String bookVehicle(@RequestBody BookingDetails bookingDetails) {
		try {
			final Optional<VehicleModel> vehicleModel = vehicleService.bookVehicle(bookingDetails);
			return vehicleModel.map(model -> model.getVehicleNumber() + " from " + model.getBranch().getName() + " booked").orElse("No "+bookingDetails.getVehicleType()+" available");
		} catch (Exception e) {
			e.printStackTrace();
			return "Failed to book vehicle";
		}
	}


	public VehicleController() {
	}

	public VehicleController(VehicleService vehicleService) {
		this.vehicleService = vehicleService;
	}
}

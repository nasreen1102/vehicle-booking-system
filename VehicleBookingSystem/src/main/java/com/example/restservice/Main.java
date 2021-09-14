package com.example.restservice;

import com.example.restservice.controller.VehicleController;
import com.example.restservice.controller.entity.BookingDetails;
import com.example.restservice.controller.entity.RentalPrice;
import com.example.restservice.controller.entity.Vehicle;
import com.example.restservice.datastore.VehicleDao;
import com.example.restservice.service.PricingStrategy;
import com.example.restservice.service.SelectionStrategyFactory;
import com.example.restservice.service.VehicleService;

import java.time.LocalDateTime;

public class Main {


	public static void main(String[] args) {
		final VehicleDao vehicleDao = new VehicleDao();
		VehicleService vehicleService = new VehicleService(vehicleDao);
		VehicleController controller = new VehicleController(vehicleService);


		System.out.println(controller.addBranch("Vasanth Vihar"));
		System.out.println(controller.addBranch("Cyber City"));
		//vehicleDao.showBranch();

		System.out.println(controller.allocatePrice(new RentalPrice("Sedan", 1L, 100.0)));
		System.out.println(controller.allocatePrice(new RentalPrice("Hatchback", 1L, 80.0)));
		System.out.println(controller.allocatePrice(new RentalPrice("Sedan", 2L, 200.0)));
		System.out.println(controller.allocatePrice(new RentalPrice("Hatchback", 2L, 50.0)));
		//vehicleDao.showPricing();

		System.out.println(controller.addVehicle(new Vehicle("DL 01 MR 9310", "Sedan", 1L)));
		System.out.println(controller.addVehicle(new Vehicle("DL 01 MR 9311", "Sedan", 2L)));
		System.out.println(controller.addVehicle(new Vehicle("DL 01 MR 9312", "Hatchback", 2L)));
		//vehicleDao.showVehicles();

		//for testing purpose
		SelectionStrategyFactory.pricingStrategy = new PricingStrategy(vehicleDao);
		LocalDateTime startTime = LocalDateTime.of(2020, 02, 29, 10, 00);
		LocalDateTime endTime = LocalDateTime.of(2020, 02, 29, 13, 00);
		LocalDateTime startTime2Pm = LocalDateTime.of(2020, 02, 29, 14, 00);
		LocalDateTime endTime3pm = LocalDateTime.of(2020, 02, 29, 15, 00);
		System.out.println(controller.bookVehicle(new BookingDetails("Sedan", startTime, endTime)));
		System.out.println(controller.bookVehicle(new BookingDetails("Sedan", startTime2Pm, endTime3pm)));
		System.out.println(controller.bookVehicle(new BookingDetails("Sedan", startTime2Pm, endTime3pm)));
		System.out.println(controller.bookVehicle(new BookingDetails("Sedan", startTime2Pm, endTime3pm)));



		//vehicleDao.showBookings();
	}
}

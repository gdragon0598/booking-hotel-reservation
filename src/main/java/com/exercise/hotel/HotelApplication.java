package com.exercise.hotel;


import com.exercise.hotel.api.AdminResource;
import com.exercise.hotel.api.HotelResource;
import com.exercise.hotel.model.Room;
import com.exercise.hotel.model.RoomType;
import com.exercise.hotel.service.CustomerService;
import com.exercise.hotel.service.ReservationService;
import com.exercise.hotel.ui.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


@SpringBootApplication
public class HotelApplication {

	public static void main(String[] args) throws IOException {
		SpringApplication.run(HotelApplication.class, args);

		CustomerService customerService = CustomerService.getInstance();
		ReservationService reservationService = ReservationService.getInstance();

		ReservationService test = ReservationService.getInstance();


		HotelResource hotelResource = new HotelResource(customerService, reservationService);
		AdminResource adminResource = new AdminResource(customerService, reservationService);

		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		ConsolePrinter consolePrinter = new ConsolePrinterImpl();
		MainMenuService mainMenuService = new MainMenuService(hotelResource, consolePrinter, bufferedReader);
		AdminMenuService adminMenuService = new AdminMenuService(hotelResource, adminResource, consolePrinter, bufferedReader);

		AdminMenuManager adminMenuManager = new AdminMenuManager(adminMenuService);
		MainMenuManager mainMenuManager = new MainMenuManager(adminMenuManager, mainMenuService);


		mainMenuManager.open();
	}
}

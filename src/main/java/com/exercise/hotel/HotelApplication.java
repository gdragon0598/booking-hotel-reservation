package com.exercise.hotel;


import com.exercise.hotel.service.CustomerService;
import com.exercise.hotel.ui.ConsolePrinter;
import com.exercise.hotel.ui.ConsolePrinterImpl;
import com.exercise.hotel.ui.MainMenuManager;
import com.exercise.hotel.ui.MainMenuService;
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
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		ConsolePrinter consolePrinter = new ConsolePrinterImpl();
		MainMenuService mainMenuService = new MainMenuService(consolePrinter, bufferedReader);
		MainMenuManager mainMenuManager = new MainMenuManager(mainMenuService,customerService);

		mainMenuManager.open();
	}

}

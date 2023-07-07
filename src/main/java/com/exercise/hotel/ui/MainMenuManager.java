package com.exercise.hotel.ui;

import com.exercise.hotel.service.CustomerService;
import com.exercise.hotel.model.Customer;

import java.io.IOException;

public class MainMenuManager implements MenuManager{
    private final MainMenuService mmService;
    private final CustomerService customerService;
    public MainMenuManager(MainMenuService mmService, CustomerService customerService) {
        this.mmService = mmService;
        this.customerService = customerService;
    }

    public void open() throws IOException {
        mmService.showMenu();
        int choice = mmService.readChoice();
        switch (choice) {
            case 3 -> customerService.addCustomer(packCustomerObject());
            case 5 -> System.exit(0);
        }
    }

    private Customer packCustomerObject() throws IOException {
        return new Customer(mmService.readEmail(), mmService.readPassword(), mmService.readFirstName(), mmService.readLastName());
    }
}

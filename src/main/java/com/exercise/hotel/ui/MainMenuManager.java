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
    @Override
    public void open() {
        mmService.showMenu();
        int choice = 0;
        try {
            choice = mmService.readChoice();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        switch (choice) {
            case 3 -> {
                try {
                    customerService.addCustomer(packCustomerObject());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            case 5 -> System.exit(0);
        }
        customerService.showAllCustomer();
    }

    private Customer packCustomerObject() throws IOException {
        return new Customer(mmService.readEmail(), mmService.readPassword(), mmService.readFirstName(), mmService.readLastName());
    }
}

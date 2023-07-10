package com.exercise.hotel.ui;

import java.io.IOException;

public class MainMenuManager implements MenuManager{
    private final AdminMenuManager adminMenuManager;
    private final MainMenuService mainMenuService;
    public MainMenuManager(AdminMenuManager adminMenuManager, MainMenuService mainMenuService) {
        this.adminMenuManager = adminMenuManager;
        this.mainMenuService = mainMenuService;
    }
    @Override
    public void open() {
        int choice = 0;
        while(choice != 5) {
            mainMenuService.showMenu();
            try {
                choice = mainMenuService.readChoice();
                switch (choice) {
                    case 1 -> {
                        mainMenuService.findAndReserveRoom();
                    }
                    case 2 -> {
                        mainMenuService.viewReservationByCurrentCustomer();
                    }
                    case 3 -> {
                        mainMenuService.addCustomer();
                    }
                    case 4 -> {
                        adminMenuManager.open();
                    }
                    case 5 -> {
                        System.exit(0);
                    }
                }
            } catch (IOException e) {

            }
        }
    }

}

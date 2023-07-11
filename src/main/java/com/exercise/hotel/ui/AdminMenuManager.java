package com.exercise.hotel.ui;

import java.io.IOException;

public class AdminMenuManager implements MenuManager {
    private final AdminMenuService adminMenuService;

    public AdminMenuManager(AdminMenuService adminMenuService) {
        this.adminMenuService = adminMenuService;
    }

    @Override
    public void open() {
        int choice = 0;
        while(choice != 8) {
            adminMenuService.showMenu();
            try {
                choice = adminMenuService.readChoice();
                switch (choice) {
                    case 1 -> {
                        adminMenuService.listAllRooms();
                    }
                    case 2 -> {

                    }
                    case 3 -> {
                        adminMenuService.listAllReservations();
                    }
                    case 4 -> {

                    }
                    case 5 -> {
                        adminMenuService.listAllCustomers();
                    }
                    case 6 -> {

                    }
                    case 7 -> {
                        adminMenuService.addRooms();
                    }
                    case 8 -> {
                        return;
                    }
                }
            } catch (IOException e) {

            }
        }
    }
}

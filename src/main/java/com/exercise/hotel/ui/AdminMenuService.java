package com.exercise.hotel.ui;

import com.exercise.hotel.api.AdminResource;
import com.exercise.hotel.api.HotelResource;
import com.exercise.hotel.model.IRoom;
import com.exercise.hotel.model.Room;
import com.exercise.hotel.model.RoomType;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AdminMenuService extends MenuService{
    private final HotelResource hotelResource;
    private final AdminResource adminResource;
    private final ConsolePrinter consolePrinter;
    private final BufferedReader bufferedReader;
    public AdminMenuService(HotelResource hotelResource, AdminResource adminResource, ConsolePrinter consolePrinter, BufferedReader bufferedReader) {
        this.adminResource = adminResource;
        this.bufferedReader = bufferedReader;
        this.consolePrinter = consolePrinter;
        this.hotelResource = hotelResource;
    }

    /**
     * main menu of ADMIN MENU
     */
    @Override
    public void showMenu() {
        consolePrinter.print("");
        consolePrinter.print("Welcome to Vanya's Hotel Reservation Administration");
        consolePrinter.print("----------------------------------------");
        consolePrinter.print("1. List all rooms");
        consolePrinter.print("2. List all free rooms within a check-in date and check-out date");
        consolePrinter.print("3. List all reservations");
        consolePrinter.print("4. List all reservations by room");
        consolePrinter.print("5. List all customers");
        consolePrinter.print("6. List all reservations by customer");
        consolePrinter.print("7. Add rooms");
        consolePrinter.print("8. Back to main menu");
        consolePrinter.print("----------------------------------------");
    }

    /**
     * UI service to ask them to choose an option, which is an integer.
     * @return an integer number represents choice
     * @throws IOException
     */
    public int readChoice() throws IOException {
        consolePrinter.print("Please enter a number to select an option");
        int choice;
        while(true) {
            try {
                choice = Integer.parseInt(bufferedReader.readLine());
                break;
            } catch (NumberFormatException e) {
                consolePrinter.print("Please enter a number to select an option");
            }
        }
        return choice;
    }

    /**
     * UI to show all current rooms to admin
     */
    public void listAllRooms() {
        consolePrinter.print("All current rooms at Vanya hotel right now: ");
        adminResource.getAllRooms().stream().forEach(x -> {
            consolePrinter.print(x.toString());
        });
    }

    /**
     * UI to show all reservations now to admin
     */
    public void listAllReservations() {
        consolePrinter.print("All reservations now at Vanya hotel: ");
        adminResource.getAllReservation().stream().forEach(x -> consolePrinter.print(x.toString()));
    }

    /**
     * UI to show all customers
     */
    public void listAllCustomers() {
        consolePrinter.print("All customers now at Vanya hotel: ");
        adminResource.getAllCustomer().stream().forEach(x -> consolePrinter.print(x.toString()));
    }

    /**
     * UI to add a room (by admin). Console prints the driven-question to user.
     * @throws IOException
     */
    public void addRooms() throws IOException {
        List<IRoom> roomList = new ArrayList<>();
        do {
            consolePrinter.print("Please input room informations");
            consolePrinter.print("Please input room number ");
            int roomNumber = Integer.parseInt(bufferedReader.readLine());
            consolePrinter.print("Please input price (float number, Example: 1.0, 2.0...");
            double price = Double.parseDouble(bufferedReader.readLine());
            consolePrinter.print("Please input room type (1: SINGLE, 2: DOUBLE, 3: DELUXE, other: PRESIDENT");
            int roomType = Integer.parseInt(bufferedReader.readLine());
            RoomType roomType1 = switch (roomType) {
                case 1 -> RoomType.SINGLE;
                case 2 -> RoomType.DOUBLE;
                case 3 -> RoomType.DELUXE;
                default -> RoomType.PRESIDENT;
            };
            roomList.add(new Room(roomNumber,price,roomType1));


        } while(readYesNoChoice());
        adminResource.addRooms(roomList);
    }

    /**
     * Ask user yes or no and read input then check.
     * @return true if user type 'y' or 'Y' otherwise false
     * @throws IOException
     */
    public boolean readYesNoChoice() throws IOException {
        consolePrinter.print("Do you want to continue (y/n)");
        String yesNo = bufferedReader.readLine();
        if(yesNo.equals("y") || yesNo.equals("Y")) {
            return true;
        }
        return false;
    }
}

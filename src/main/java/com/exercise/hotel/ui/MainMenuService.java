package com.exercise.hotel.ui;

import com.exercise.hotel.api.HotelResource;
import com.exercise.hotel.model.Customer;
import com.exercise.hotel.model.IRoom;
import com.exercise.hotel.model.Reservation;
import com.exercise.hotel.util.CustomerValidate;
import java.io.BufferedReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;

public class MainMenuService extends MenuService {
    private final HotelResource hotelResource;
    private Customer currentCustomer;
    private final ConsolePrinter consolePrinter;
    private final BufferedReader bufferedReader;
    public MainMenuService(HotelResource hotelResource, ConsolePrinter consolePrinter, BufferedReader bufferedReader) {
        this.hotelResource = hotelResource;
        this.consolePrinter = consolePrinter;
        this.bufferedReader = bufferedReader;
    }
    @Override
    public void showMenu() {
        consolePrinter.print("");
        consolePrinter.print("Welcome to Vanya's Hotel Reservation App");
        consolePrinter.print("----------------------------------------");
        consolePrinter.print("1. Find and reserve a room");
        consolePrinter.print("2. See my reservations");
        consolePrinter.print("3. Create an account");
        consolePrinter.print("4. Admin");
        consolePrinter.print("5. Exit");
        consolePrinter.print("----------------------------------------");
    }

    /**
     * UI service to read Email from the console then check its validity
     * @return a valid string email
     * @throws IOException
     */
    public String readEmail() throws IOException {
        consolePrinter.print("Please input your email");
       String email = bufferedReader.readLine();
       while (!CustomerValidate.emailValidate(email)) {
           consolePrinter.print("Email is not valid, please input again");
           email = bufferedReader.readLine();
       }
       return email;
    }

    /**
     *  UI service to read first name from console
     * @return first name string
     * @throws IOException
     */
    public String readFirstName() throws IOException {
        consolePrinter.print("Please input your first name");
        String firstName = bufferedReader.readLine();
        return firstName;
    }

    /**
     * UI service to read last name from console
     * @return last name string
     * @throws IOException
     */
    public String readLastName() throws IOException {
        consolePrinter.print("Please input your last name");
        String lastName = bufferedReader.readLine();
        return lastName;
    }

    /**
     * UI service to read password from console
     * @return password string
     * @throws IOException
     */
    public String readPassword() throws IOException {
        consolePrinter.print("Please input your password");
        String password = bufferedReader.readLine();
        return password;
    }

    /**
     * UI service to ask user to choose an option, which is an integer.
     * @return an integer number represents choice
     * @throws IOException
     */
    public int readChoice() throws IOException{
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
     * Ask user to input Check-in and Check-out date, ensure the user input right date format
     * @return  An ArrayList of LocalDate,in which, the first item is check-in date, the second one is check-out date
     * @throws IOException
     */
    public List<LocalDate> readCheckInOutDate() throws IOException {
        LocalDate checkInDate, checkOutDate;
        while (true) {
            try {
                consolePrinter.print("Enter check-in date in format mm/dd/yyyy Example: 07/10/2023");
                String checkIn = bufferedReader.readLine();
                checkInDate = LocalDate.parse(checkIn, DateTimeFormatter.ofPattern("MM/dd/yyyy"));
                do{
                    consolePrinter.print("Enter check-out date (must be after the check-in date) in format mm/dd/yyyy Example: 07/10/2023");
                    String checkOut = bufferedReader.readLine();
                    checkOutDate = LocalDate.parse(checkOut, DateTimeFormatter.ofPattern("MM/dd/yyyy"));
                }
                while(checkOutDate.isBefore(checkInDate));
                break;
            } catch (DateTimeParseException e) {
                consolePrinter.print("Wrong format date");
            }
        }
        LocalDate finalCheckInDate = checkInDate;
        LocalDate finalCheckOutDate = checkOutDate;
        List<LocalDate> checkInCheckOut = new ArrayList<>(){
            {
                add(finalCheckInDate);
                add(finalCheckOutDate);
            }
        };
        return checkInCheckOut;
    }

    /**
     * from a list of free rooms, print to console
     * @param freeRooms         a list of free room
     * @return                  true if there is any free room, else false
     */
    public boolean showFreeRooms(List<IRoom> freeRooms) {
        consolePrinter.print("Following rooms are available for booking:");
        HashMap<Integer, IRoom> freeRoomsIndexed = freeRooms
                .stream()
                .collect(HashMap<Integer, IRoom>::new,
                        (map, streamValue) -> map.put(map.size(), streamValue),
                        (map, map2) -> {
                        });
        freeRoomsIndexed.forEach((k, v) -> System.out.println(k + ":" + v));
        if(freeRooms.size() > 0) {
            consolePrinter.print("Would you like to book one of the rooms above? (y/n)");
            return true;
        } else {
            consolePrinter.print("There is no free room in from that check-in date to check-out date");
        }
        return false;
    }

    /**
     * Ask user yes or no and read input then check.
     * @return true if user type 'y' or 'Y' otherwise false
     * @throws IOException
     */
    public boolean readYesNoChoice() throws IOException {
        String yesNo = bufferedReader.readLine();
        if(yesNo.equals("y") || yesNo.equals("Y")) {
            return true;
        }
        return false;
    }

    /**
     * Ask user to input account information, return in a dictionary
     * @return  the value of 'email' key of the Dictionary is the email string, the value of 'password' key is the password string
     * @throws IOException
     */
    public Dictionary<String, String> askAccount() throws IOException {
        consolePrinter.print("Do you have account? (y/n)");
        Dictionary<String, String> authInformation = null;
        if(readYesNoChoice()) {
            String email = readEmail();
            String password = readPassword();
            authInformation = new Hashtable<>();
            authInformation.put("email", email);
            authInformation.put("password", password);
        } else {
            consolePrinter.print("Go back main menu and create account");
        }
        return authInformation;
    }

    /**
     * notify the successful reservation
     * @param room
     */
    public void reserveRoomSuccess(IRoom room) {
        consolePrinter.print("Reserve successfully " + room);
    }

    /**
     * Find and reserve a room
     * @throws IOException
     */
    public void findAndReserveRoom() throws IOException {
        List<LocalDate> checkInOutDate = readCheckInOutDate();
        List<IRoom> freeRooms = hotelResource.findFreeRooms(checkInOutDate.get(0), checkInOutDate.get(1));
        if(showFreeRooms(freeRooms) && readYesNoChoice()) {
            //There are some free rooms and customer agree to book one
            int roomChoice = readChoice();
            if(loginAccount()) {
                reserveRoomSuccess(freeRooms.get(roomChoice));
                hotelResource.reserveARoom(new Reservation(currentCustomer, checkInOutDate.get(0), checkInOutDate.get(1), freeRooms.get(roomChoice)));
            }
        }
    }

    /**
     * call api from hotelResource to login
     * @return  true if already logged in ,else false
     * @throws IOException
     */
    private boolean loginAccount() throws IOException {
        if(currentCustomer != null) return true;
        Dictionary<String, String> accountInfo = askAccount();
        if(accountInfo != null && hotelResource.verifyCustomer(accountInfo.get("email"), accountInfo.get("password"))) {
            currentCustomer = hotelResource.getCustomerByEmail(accountInfo.get("email"));
            return true;
        }
        return false;
    }

    /**
     * if already logged in, show reservation of the current customer otherwise proceed to log-in
     * @throws IOException
     */
    public void viewReservationByCurrentCustomer() throws IOException {
        if(loginAccount()) {
            List<Reservation> reservationList = hotelResource.getReservationByCustomer(currentCustomer);
            reservationList.stream().forEach(x -> consolePrinter.print(x.toString()));
        }
    }

    /**
     * pack input (email, password, first name, last name) into a customer object
     * @return a customer object
     * @throws IOException
     */
    private Customer packCustomerObject() throws IOException {
        return new Customer(readEmail(), readPassword(), readFirstName(), readLastName());
    }

    /**
     * call hotelResource api to add a customer
     * @throws IOException
     */
    public void addCustomer() throws IOException {
        hotelResource.addCustomer(packCustomerObject());
    }
}

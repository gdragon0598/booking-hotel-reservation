package com.exercise.hotel.ui;


import com.exercise.hotel.util.CustomerValidate;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.InputMismatchException;


public class MainMenuService extends MenuService {
    private final ConsolePrinter consolePrinter;
    private final BufferedReader bufferedReader;
    public MainMenuService(ConsolePrinter consolePrinter, BufferedReader bufferedReader) {
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
    public String readEmail() throws IOException {
        consolePrinter.print("Please input your email");
       String email = bufferedReader.readLine();
       while (!CustomerValidate.emailValidate(email)) {
           consolePrinter.print("Email is not valid, please input again");
           email = bufferedReader.readLine();
       }
       return email;
    }

    public String readFirstName() throws IOException {
        consolePrinter.print("Please input your first name");
        String firstName = bufferedReader.readLine();
        return firstName;
    }

    public String readLastName() throws IOException {
        consolePrinter.print("Please input your last name");
        String lastName = bufferedReader.readLine();
        return lastName;
    }

    public String readPassword() throws IOException {
        consolePrinter.print("Please input your password");
        String password = bufferedReader.readLine();
        return password;
    }

    public int readChoice() throws IOException{
        consolePrinter.print("Please enter a number to select a menu option");
        int choice;
        while(true) {
            try {
                choice = Integer.parseInt(bufferedReader.readLine());
                break;
            } catch (NumberFormatException e) {
                consolePrinter.print("Please enter a number to select a menu option");
            }
        }
        return choice;
    }
}

package com.exercise.hotel.model;

import com.exercise.hotel.util.CustomerValidate;

public class Customer {
    private final String email;
    private final String password;
    private final String firstName;
    private final String lastName;

    public Customer(String email, String password, String firstName, String lastName) {
        if (CustomerValidate.emailValidate(email)) {
            this.email = email;
        } else {
            throw new IllegalArgumentException("Email is invalid");
        }
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }
}

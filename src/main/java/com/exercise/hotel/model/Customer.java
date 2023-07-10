package com.exercise.hotel.model;
import com.exercise.hotel.util.CustomerValidate;
import java.util.Objects;
public class Customer {
    private final String email;   //use this as ID in the service collection
    private String password;
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
    public Customer(String email, String firstName, String lastName) {
        if (CustomerValidate.emailValidate(email)) {
            this.email = email;
        } else {
            throw new IllegalArgumentException("Email is invalid");
        }
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
    public boolean authenticate(String password) {
        return this.password.equals(password);
    }
    @Override
    public String toString() {
        return "Customer{" +
                "email='" + email + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return Objects.equals(email, customer.email);
    }
}

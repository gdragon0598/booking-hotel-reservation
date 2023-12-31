package com.exercise.hotel.service;

import com.exercise.hotel.model.Customer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CustomerService {
    /*
    This class composes a collection of customers at runtime. Other packages can view this as a database interface.
    Make this class a singleton
     */
    private static CustomerService csInstance = null;
    private Map<String, Customer> customerMap;
    //Make the constructor private
    private CustomerService() {
        customerMap = new HashMap<>();
    }
    public static CustomerService getInstance() {
        if(csInstance == null)
            csInstance = new CustomerService();
        return csInstance;
    }
    /**
     * Creates a new {@link Customer} and records it if no customer already recorded with the provided email.
     * *@param email                     string, email of the customer
     * *@param firstName                 string, first name of the customer
     * *@param lastName                  string, last name of the customer
     * *@throws IllegalArgumentException if a customer with the supplied email was already recorded */
    public boolean addCustomer(Customer customer) {
        if(customerMap.containsKey(customer.getEmail()))
            throw new IllegalArgumentException("Customer with this email is " + "already registered.");
        customerMap.put(customer.getEmail(), customer);
        return true;
    }

    /**
     * This method is to check the if the email is belonged to a customer and the password is valid with that email
     * @param email     a String,  email of the customer
     * @param password  a String,  password of the customer
     * @return true if email and password are valid
     */
    public boolean verifyCustomer(String email, String password) {
        Customer tmp = customerMap.getOrDefault(email, null);
        if(tmp == null)
            return false;
        else return tmp.authenticate(password);
    }

    /**
     * This method get all customers from the Collection of customer.
     * @return  a List of Customer
     */
    public List<Customer> getAllCustomer() {
        List<Customer> list = new ArrayList<Customer>(customerMap.values());
        return list;
    }

    /**
     * This method get the customer whose email is given.
     * @param email String, email of the customer
     * @return  Customer, the customer, of which is the given email
     */
    public Customer getCustomerByEmail(String email) {
        return customerMap.get(email);
    }
}

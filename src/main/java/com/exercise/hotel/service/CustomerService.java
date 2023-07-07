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

    //Method to get the singleton instance
    public static CustomerService getInstance() {
        if(csInstance == null)
            csInstance = new CustomerService();
        return csInstance;
    }

    //Simple crud
    public boolean addCustomer(Customer customer) {
        if(customerMap.putIfAbsent(customer.getEmail(),customer) == null) {
            return true;
        } else {
            return false;
        }
    }
    public boolean verifyCustomer(String email, String password) {
        Customer tmp = customerMap.getOrDefault(email, null);
        if(tmp == null)
            return false;
        else {
            return tmp.authenticate(password);
        }
    }

    //this is used for test
    public void showAllCustomer() {
        List<Customer> list = new ArrayList<Customer>(customerMap.values());
        list.stream().forEach(System.out::println);
    }

}

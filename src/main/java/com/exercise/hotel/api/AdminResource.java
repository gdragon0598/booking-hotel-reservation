package com.exercise.hotel.api;

import com.exercise.hotel.model.Customer;
import com.exercise.hotel.model.IRoom;
import com.exercise.hotel.model.Reservation;
import com.exercise.hotel.service.CustomerService;
import com.exercise.hotel.service.ReservationService;

import java.util.List;

public class AdminResource {
    private  CustomerService customerService;
    private  ReservationService reservationService;
    public AdminResource(CustomerService customerService, ReservationService reservationService) {
        this.customerService = customerService;
        this.reservationService = reservationService;
    }
    public Customer getCustomerByEmail(String email) {
        return customerService.getCustomerByEmail(email);
    }
    public void addRooms(List<IRoom> roomList) {
        roomList.parallelStream().forEach(room -> {
            reservationService.addARoom(room);
        });
    }
    public List<IRoom> getAllRooms() {
        return reservationService.getAllRooms();
    }
    public List<Reservation> getAllReservation() {
        return reservationService.getAllReservations();
    }
    public List<Customer> getAllCustomer() {
        return customerService.getAllCustomer();
    }
}


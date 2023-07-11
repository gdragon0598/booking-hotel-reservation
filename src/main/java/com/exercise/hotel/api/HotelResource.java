package com.exercise.hotel.api;

import com.exercise.hotel.model.Customer;
import com.exercise.hotel.model.IRoom;
import com.exercise.hotel.model.Reservation;
import com.exercise.hotel.service.CustomerService;
import com.exercise.hotel.service.ReservationService;

import java.time.LocalDate;
import java.util.List;

public class HotelResource {
    /*
    * This class is a wrapper for CustomerService and ReservationService and exposes APIs to UI
    * */
    private final CustomerService customerService;
    private final ReservationService reservationService;

    public HotelResource(CustomerService customerService, ReservationService reservationService) {
        this.customerService = customerService;
        this.reservationService = reservationService;
    }

    public List<Reservation> getReservationByCustomer(Customer currentCustomer) {
        return reservationService.getReservationByCustomer(currentCustomer);
    }

    public void addCustomer(Customer customer) {
        customerService.addCustomer(customer);
    }

    public List<IRoom> findFreeRooms(LocalDate checkInDate, LocalDate checkOutDate) {
        return reservationService.findFreeRooms(checkInDate, checkOutDate);
    }

    public void reserveARoom(Reservation reservation) {
        reservationService.reserveARoom(reservation);
    }

    public Customer getCustomerByEmail(String email) {
        return customerService.getCustomerByEmail(email);
    }

    public boolean verifyCustomer(String email, String password) {
        return customerService.verifyCustomer(email, password);
    }
}

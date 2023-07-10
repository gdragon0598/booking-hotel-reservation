package com.exercise.hotel.model;
import java.time.LocalDate;
import java.util.Objects;
public class Reservation {
    private final Customer customer;
    private final LocalDate checkInDate;
    private final LocalDate checkOutDate;
    private final IRoom reservedRoom;
    public Reservation(Customer customer, LocalDate checkInDate, LocalDate checkOutDate, IRoom reservedRoom) {
        if(checkInDate.isAfter(checkOutDate))
            throw new IllegalArgumentException("Check-in date must be before check-out date");
        this.customer = customer;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.reservedRoom = reservedRoom;
    }
    public LocalDate getCheckInDate() {
        return checkInDate;
    }
    public LocalDate getCheckOutDate() {
        return checkOutDate;
    }
    public IRoom getReservedRoom() {
        return reservedRoom;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reservation that = (Reservation) o;
        return Objects.equals(checkInDate, that.checkInDate) && Objects.equals(checkOutDate, that.checkOutDate) && Objects.equals(reservedRoom, that.reservedRoom);
    }
    @Override
    public int hashCode() {
        return Objects.hash(checkInDate.hashCode(), checkOutDate.hashCode(), reservedRoom.hashCode());
    }
    public boolean isOverlap(LocalDate checkInDate, LocalDate checkOutDate) {
        if (checkOutDate.isBefore(checkInDate) || checkInDate.isAfter(checkOutDate))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "customer=" + customer +
                ", checkInDate=" + checkInDate +
                ", checkOutDate=" + checkOutDate +
                ", reservedRoom=" + reservedRoom +
                '}';
    }

    public Customer getCustomer() {
        return customer;
    }
}

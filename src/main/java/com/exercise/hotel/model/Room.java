package com.exercise.hotel.model;

import java.util.Objects;

public class Room implements IRoom{
    private int roomNumber;
    private double price;
    private RoomType roomType;
    public Room(int roomNumber, double price, RoomType roomType) {
        this.roomNumber = roomNumber;
        this.price = price;
        this.roomType = roomType;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    @Override
    public int getRoomNumber() {
        return roomNumber;
    }
    public double getPrice() {
        return price;
    }
    public RoomType getRoomType() {
        return roomType;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Room room = (Room) o;
        return roomNumber == room.roomNumber && Double.compare(room.price, price) == 0 && roomType == room.roomType;
    }
    @Override
    public int hashCode() {
        return Objects.hash(roomNumber);
    }
    @Override
    public String toString() {
        return "Room " +
                "number: " + roomNumber +
                ", price: " + price +
                ", roomType: " + roomType;
    }
}

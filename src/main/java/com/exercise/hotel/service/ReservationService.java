package com.exercise.hotel.service;

import com.exercise.hotel.model.Customer;
import com.exercise.hotel.model.IRoom;
import com.exercise.hotel.model.Reservation;
import java.time.LocalDate;
import java.util.*;

public class ReservationService {
    private static ReservationService reservationService = null;
    private Map<IRoom, List<Reservation>> reservationMap;
    private Map<Integer, IRoom> roomMap;
    private ReservationService() {
        roomMap = new HashMap<>();
        reservationMap = new HashMap<>();
    }

    /**
     * Use this method to get instance from this class.
     * @return a ReservationService instance
     */
    public static ReservationService getInstance() {
        if(reservationService == null)
            reservationService = new ReservationService();
        return reservationService;
    }

    /**
     * This method is to add a room to the current roomMap, which is a collection of room
     * @param room room needs adding to the current collection of room.
     * @throws IllegalArgumentException if room with the given room number already exists then throw this exception
     */
    public void addARoom(IRoom room) {
        if(roomMap.containsKey(room.getRoomNumber())) {
            throw new IllegalArgumentException("Room with number " + room.getRoomNumber() + " already exists.");
        }
        roomMap.put(room.getRoomNumber(), room);
    }

    /**
     * Get a room with the given room number
     * @param roomNumber
     * @return a Room object
     */
    public IRoom getARoom(int roomNumber) {
        if (!roomMap.containsKey(roomNumber)) {
            throw new IllegalArgumentException("There is no room with number " + roomNumber);
        }
        return roomMap.get(roomNumber);
    }

    /**
     * Add a Reservation object to the current Collection of Reservation. If successfully add the reservation return true.
     * @param reservation
     * @return true if successfully add the reservation else false
     */
    public boolean reserveARoom(Reservation reservation) {
        IRoom reservedRoom = reservation.getReservedRoom();
        boolean isSuccessReserve = true;
        if(!reservationMap.containsKey(reservedRoom)) {
            reservationMap.computeIfAbsent(reservedRoom, k -> new ArrayList<>()).add(reservation);
        } else {
           if(checkRoomStatus(reservedRoom, reservation.getCheckInDate(),reservation.getCheckOutDate())) {
               reservationMap.compute(reservedRoom, (k,v)-> {
                   v.add(reservation);
                   return v;
               });
           } else isSuccessReserve = false;
        }
        return isSuccessReserve;
    }

    /**
     * Method to find from the current collection of room which rooms are free within the check-in date and check-out date
     * @param checkInDate
     * @param checkOutDate
     * @return  a List of free room
     */
    public List<IRoom> findFreeRooms(LocalDate checkInDate, LocalDate checkOutDate) {
        List<IRoom> allCurrentRooms =  new ArrayList<IRoom>(roomMap.values());
        allCurrentRooms = allCurrentRooms.parallelStream()
                        .filter(room -> {
                           return checkRoomStatus(room, checkInDate, checkOutDate);
                        }).toList();
        return allCurrentRooms;
    }

    /**
     * This method is to check a status of a room within a check-in date and a check-out date, whether it is free or reserved already
     * @param room     room to check
     * @param checkinDate
     * @param checkoutDate
     * @return  true if it is free else false
     */
    private boolean checkRoomStatus(IRoom room, LocalDate checkinDate, LocalDate checkoutDate) {
        List<Reservation> reservations = reservationMap.get(room);
        boolean isAvailable = true;
        if(reservations != null) {
            for (Reservation re : reservations) {
                if(re.isOverlap(checkinDate, checkoutDate)) {
                    isAvailable = false;
                    break;
                }
            }
        }
        return isAvailable;
    }

    /**
     * This returns a list of all reservations at the current
     * @return  a List of Reservation
     */
    public List<Reservation> getAllReservations() {
        List<List<Reservation>> reservationList = new ArrayList<>(reservationMap.values());
        return reservationList.stream().flatMap(s -> s.stream()).toList();
    }

    /**
     * This returns a list of all reservations of the given customer.
     * @param currentCustomer   The customer whose reservations are returned
     * @return                  A List of reservations
     */
    public List<Reservation> getReservationByCustomer(Customer currentCustomer) {
        List<List<Reservation>> reservationList = new ArrayList<>(reservationMap.values());
        return reservationList.parallelStream().flatMap(s -> s.stream()).filter(reservation -> {
            return reservation.getCustomer().equals(currentCustomer);
        }).toList();
    }

    /**
     * This returns a list of all rooms in the collection
     * @return  A List of room
     */
    public List<IRoom> getAllRooms() {
        List<IRoom> roomList = new ArrayList<>(roomMap.values());
        return roomList;
    }
}

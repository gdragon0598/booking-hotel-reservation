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
    public void addARoom(IRoom room) {
        if(roomMap.containsKey(room.getRoomNumber())) {
            throw new IllegalArgumentException("Room with number " + room.getRoomNumber() + " already exists.");
        }
        roomMap.put(room.getRoomNumber(), room);
    }
    public IRoom getARoom(int roomNumber) {
        if (!roomMap.containsKey(roomNumber)) {
            throw new IllegalArgumentException("There is no room with number " + roomNumber);
        }
        return roomMap.get(roomNumber);
    }

    /**
     *
     * @param reservation
     * @return
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
    public List<IRoom> findFreeRooms(LocalDate checkInDate, LocalDate checkOutDate) {
        List<IRoom> allCurrentRooms =  new ArrayList<IRoom>(roomMap.values());
        allCurrentRooms = allCurrentRooms.parallelStream()
                        .filter(room -> {
                           return checkRoomStatus(room, checkInDate, checkOutDate);
                        }).toList();
        return allCurrentRooms;
    }
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

    public List<Reservation> getAllReservations() {
        List<List<Reservation>> reservationList = new ArrayList<>(reservationMap.values());
        return reservationList.stream().flatMap(s -> s.stream()).toList();
    }

    public List<Reservation> getReservationByCustomer(Customer currentCustomer) {
        List<List<Reservation>> reservationList = new ArrayList<>(reservationMap.values());
        return reservationList.parallelStream().flatMap(s -> s.stream()).filter(reservation -> {
            return reservation.getCustomer().equals(currentCustomer);
        }).toList();
    }

    public List<IRoom> getAllRooms() {
        List<IRoom> roomList = new ArrayList<>(roomMap.values());
        return roomList;
    }
}

package com.exercise.hotel;

import com.exercise.hotel.model.Reservation;
import com.exercise.hotel.model.Room;
import com.exercise.hotel.model.RoomType;
import com.exercise.hotel.service.ReservationService;
import com.exercise.hotel.util.CustomerValidate;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

public class ReservationServiceTest {
    @Test
    void testReservationService() {
        ReservationService test = ReservationService.getInstance();
        test.addARoom(new Room(1, 10, RoomType.SINGLE));
        test.addARoom(new Room(2, 10, RoomType.SINGLE));
        test.addARoom(new Room(3, 10, RoomType.SINGLE));
        test.addARoom(new Room(4, 10, RoomType.SINGLE));
        test.addARoom(new Room(5, 10, RoomType.SINGLE));
        test.addARoom(new Room(6, 10, RoomType.SINGLE));
        test.addARoom(new Room(7, 10, RoomType.SINGLE));
        test.addARoom(new Room(8, 10, RoomType.SINGLE));
        test.addARoom(new Room(9, 10, RoomType.SINGLE));
        test.addARoom(new Room(10, 10, RoomType.SINGLE));


        test.reserveARoom(new Reservation(null, LocalDate.of(2023,6,30), LocalDate.now(), new Room(1, 10, RoomType.SINGLE)));
        test.findFreeRooms(LocalDate.of(2023,7,1), LocalDate.now()).stream().forEach(System.out::println);
    }
}

package com.exercise.hotel.api;

import com.exercise.hotel.model.IRoom;
import com.exercise.hotel.model.Room;
import com.exercise.hotel.model.RoomType;
import com.exercise.hotel.service.CustomerService;
import com.exercise.hotel.service.ReservationService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
//@RunWith(SpringJUnit4ClassRunner.class)
public class AdminResourceTest {
    private static AdminResource adminResource;
    private static CustomerService customerService;

    private static ReservationService reservationService;


    @BeforeAll
    static void init() {
        customerService = CustomerService.getInstance();
        reservationService = ReservationService.getInstance();
        adminResource = new AdminResource(customerService, reservationService);
    }
    @Test
    void testAddRoom() {
        int numOfRoom = 10;
        List<IRoom> roomList = new ArrayList<IRoom>();
        for(int i = 0; i < numOfRoom; ++i) {
            roomList.add(new Room(i,10,RoomType.SINGLE));
        }
        adminResource.addRooms(roomList);
        assertEquals(reservationService.getAllRooms().size(),roomList.size());
    }
    @Test
    void testGetAllRooms() {
        List<IRoom> roomList = adminResource.getAllRooms();
        assertEquals(roomList.size(),10);
    }
}

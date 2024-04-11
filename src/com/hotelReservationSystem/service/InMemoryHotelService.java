package com.hotelReservationSystem.service;


import com.hotelReservationSystem.model.Hotel;
import com.hotelReservationSystem.model.Reservation;
import com.hotelReservationSystem.model.Room;
import com.hotelReservationSystem.model.RoomType;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemoryHotelService implements HotelService {

    private final Map<String, Hotel> hotels; // Map to store hotels by name (key)

    public InMemoryHotelService() {
        hotels = createSampleHotels();
    }

    private Map<String, Hotel> createSampleHotels() {
        Map<String, Hotel> hotelsMap = new HashMap<>();

        // Create sample hotels with rooms
        Hotel hotel1 = new Hotel("Grand Stay");
        hotel1.addRoom(new Room(101, RoomType.SINGLE, 50.0, 1));
        hotel1.addRoom(new Room(102, RoomType.DOUBLE, 75.0, 2));
        hotel1.addRoom(new Room(103, RoomType.KING, 100.0, 2));
        hotelsMap.put(hotel1.getName(), hotel1);

        Hotel hotel2 = new Hotel("Cozy Comfort");
        hotel2.addRoom(new Room(201, RoomType.SINGLE, 45.0, 1));
        hotel2.addRoom(new Room(202, RoomType.DOUBLE, 60.0, 2));
        hotel2.addRoom(new Room(203, RoomType.SUITE, 120.0, 4));
        hotelsMap.put(hotel2.getName(), hotel2);

        return hotelsMap;
    }

    @Override
    public List<Hotel> findAvailableHotels(Reservation reservation) {
        List<Hotel> availableHotels = new ArrayList<>();
        for (Hotel hotel : hotels.values()) {
            if (hotel.findAvailableRooms(reservation).size() > 0) {
                availableHotels.add(hotel);
            }
        }
        return availableHotels;
    }

    @Override
    public boolean bookRoom(Reservation reservation, Hotel hotel, Room room) {
        if (hotel.bookRoom(reservation, room)) {
            return true; // Booking successful
        } else {
            return false; // Room already booked or other error
        }
    }
}


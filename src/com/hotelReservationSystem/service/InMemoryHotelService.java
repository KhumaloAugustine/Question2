package com.hotelReservationSystem.service;

import com.hotelReservationSystem.model.Hotel;
import com.hotelReservationSystem.model.Reservation;
import com.hotelReservationSystem.model.Room;
import com.hotelReservationSystem.model.RoomType;

import java.util.ArrayList;
// Import HashMap for efficient hotel storage by name
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemoryHotelService implements HotelService {

    // Stores hotels using their names as keys for easy retrieval
    private final Map<String, Hotel> hotels;

    public InMemoryHotelService() {
        // Initialize the hotel map with some sample hotels during startup
        hotels = createSampleHotels();
    }

    // Create some sample hotels with various room types for testing purposes
    private Map<String, Hotel> createSampleHotels() {
        Map<String, Hotel> hotelsMap = new HashMap<>();

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
        // Search through all hotels
        for (Hotel hotel : hotels.values()) {
            // Check if the hotel has rooms available that match the reservation requirements
            if (!hotel.findAvailableRooms(reservation).isEmpty()) {
                availableHotels.add(hotel);
            }
        }
        return availableHotels;
    }

    @Override
    public boolean bookRoom(Reservation reservation, Hotel hotel, Room room) {
        // Attempt to book the room in the chosen hotel using the hotel's booking method
        if (hotel.bookRoom(reservation, room)) {
            return true; // Booking successful
        } else {
            return false; // Room already booked or other error during booking
        }
    }
}
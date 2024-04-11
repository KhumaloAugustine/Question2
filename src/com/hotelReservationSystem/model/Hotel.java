package com.hotelReservationSystem.model;

import java.util.ArrayList;
import java.util.List;

public class Hotel {

    // This stores the hotel's name
    private final String name;

    // This list stores all the rooms in the hotel
    private final List<Room> rooms;

    // Constructor to create a new Hotel with a name
    public Hotel(String name) {
        this.name = name;
        // Initialize the rooms list as an empty ArrayList
        this.rooms = new ArrayList<>();
    }

    // Get the hotel's name
    public String getName() {
        return name;
    }

    // Get the list of all rooms in the hotel
    public List<Room> getRooms() {
        // Return a copy of the list to avoid modifying the original list
        return new ArrayList<>(rooms);
    }

    // Add a new room to the hotel
    public void addRoom(Room room) {
        rooms.add(room);
    }

    // Find all rooms that are available based on a reservation request
    // (considering room capacity and availability)
    public List<Room> findAvailableRooms(Reservation request) {
        List<Room> availableRooms = new ArrayList<>();
        for (Room room : rooms) {
            // Check if the room is available and has enough capacity for guests
            if (room.isAvailable() && room.getCapacity() >= request.getNumGuests()) {
                availableRooms.add(room);
            }
        }
        return availableRooms;
    }

    // Try to book a specific room for a reservation
    // This only succeeds if the room is currently available
    public boolean bookRoom(Reservation reservation, Room room) {
        if (room.isAvailable()) {
            room.setAvailable(false); // Mark the room as booked
            return true;
        } else {
            return false; // Booking failed because room is unavailable
        }
    }

    @Override
    public String toString() {
        return name + " Hotel";
    }
}
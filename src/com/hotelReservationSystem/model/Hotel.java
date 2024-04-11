package com.hotelReservationSystem.model;

import java.util.ArrayList;
import java.util.List;

public class Hotel {

    private final String name;
    private final List<Room> rooms;

    public Hotel(String name) {
        this.name = name;
        this.rooms = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public void addRoom(Room room) {
        rooms.add(room);
    }

    public List<Room> findAvailableRooms(Reservation request) {
        List<Room> availableRooms = new ArrayList<>();
        for (Room room : rooms) {
            if (room.isAvailable() && room.getCapacity() >= request.getNumGuests()) {
                availableRooms.add(room);
            }
        }
        return availableRooms;
    }

    public boolean bookRoom(Reservation reservation, Room room) {
        if (room.isAvailable()) {
            room.setAvailable(false); // Mark room as booked
            return true;
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        return name + " Hotel";
    }
}
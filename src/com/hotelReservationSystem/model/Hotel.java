package com.hotelReservationSystem.model;

import java.util.ArrayList;
import java.util.List;

public class Hotel {
    private String name;
    private String location;
    private double rating;
    private String priceRange;
    private List<Room> rooms;

    public Hotel(String name) {
        this.name = name;
        this.rooms = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public String getPriceRange() {
        return priceRange;
    }

    public void setPriceRange(String priceRange) {
        this.priceRange = priceRange;
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public void addRoom(Room room) {
        rooms.add(room);
    }

    public List<Room> findAvailableRooms(Reservation reservation) {
        List<Room> availableRooms = new ArrayList<>();
        for (Room room : rooms) {
            if (room.isAvailable() && room.getCapacity() >= reservation.getNumGuests()) {
                availableRooms.add(room);
            }
        }
        return availableRooms;
    }

    public boolean bookRoom(Reservation reservation, Room room) {
        if (room.isAvailable() && room.getCapacity() >= reservation.getNumGuests()) {
            room.setReservation(reservation);
            room.setAvailable(false);
            reservation.setHotelName(name);
            reservation.setRoomNumber(room.getNumber());
            return true;
        }
        return false;
    }
}

package com.hotelReservationSystem.model;

public class Room {

    private final int roomNumber;
    private final RoomType type;
    private final double pricePerNight;
    private final int capacity;
    private boolean available; // Flag indicating if the room is available

    public Room(int roomNumber, RoomType type, double pricePerNight, int capacity) {
        this.roomNumber = roomNumber;
        this.type = type;
        this.pricePerNight = pricePerNight;
        this.capacity = capacity;
        this.available = true; // Initially available
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public RoomType getType() {
        return type;
    }

    public double getPricePerNight() {
        return pricePerNight;
    }

    public int getCapacity() {
        return capacity;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    @Override
    public String toString() {
        return "Room " + roomNumber + " (" + type + "): $" + pricePerNight + "/night, capacity " + capacity;
    }
}
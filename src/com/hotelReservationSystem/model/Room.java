package com.hotelReservationSystem.model;

public class Room {

    // Unique number identifying this room
    private final int roomNumber;

    // Type of room (e.g., Single, Double, Suite)
    private final RoomType type;

    // Price charged per night for this room
    private final double pricePerNight;

    // Maximum number of guests allowed in this room
    private final int capacity;

    // Indicates if the room is currently available for booking
    private boolean available;

    // Constructor to create a new Room with details
    public Room(int roomNumber, RoomType type, double pricePerNight, int capacity) {
        this.roomNumber = roomNumber;
        this.type = type;
        this.pricePerNight = pricePerNight;
        this.capacity = capacity;
        // Set the room to available by default
        this.available = true;
    }

    // Get the unique room number
    public int getRoomNumber() {
        return roomNumber;
    }

    // Get the type of room (e.g., Single, Double, Suite)
    public RoomType getType() {
        return type;
    }

    // Get the price charged per night for this room
    public double getPricePerNight() {
        return pricePerNight;
    }

    // Get the maximum number of guests allowed in this room
    public int getCapacity() {
        return capacity;
    }

    // Check if the room is currently available for booking
    public boolean isAvailable() {
        return available;
    }

    // Update the availability status of the room
    public void setAvailable(boolean available) {
        this.available = available;
    }

    // Override the default toString method to provide a clear description of the room
    @Override
    public String toString() {
        return "Room " + roomNumber + " (" + type + "): R" + pricePerNight + "/night, capacity " + capacity;
    }
}
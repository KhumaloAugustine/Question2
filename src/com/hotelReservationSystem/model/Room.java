package com.hotelReservationSystem.model;

public class Room {
    private int number;
    private RoomType type;
    private double pricePerNight;
    private int capacity;
    private boolean available;
    private Reservation reservation;

    public Room(int number, RoomType type, double pricePerNight, int capacity) {
        this.number = number;
        this.type = type;
        this.pricePerNight = pricePerNight;
        this.capacity = capacity;
        this.available = true;
    }

    public int getNumber() {
        return number;
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

    public Reservation getReservation() {
        return reservation;
    }

    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
    }

    @Override
    public String toString() {
        return "Room " + number + " (" + type + "): R" + pricePerNight + "/night, capacity " + capacity;
    }
}
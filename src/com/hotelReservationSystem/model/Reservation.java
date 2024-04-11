package com.hotelReservationSystem.model;

import java.time.LocalDate;

public class Reservation {

    // Guest's name for this reservation
    private final String guestName;

    // Number of guests staying in this reservation
    private final int numGuests;

    // Date the guests are arriving
    private final LocalDate arrivalDate;

    // Date the guests are departing
    private final LocalDate departureDate;

    // Reference to the Hotel this reservation is for (initially null)
    private Hotel hotel;

    // Reference to the specific Room assigned to this reservation (initially null)
    private Room room;

    // Constructor to create a new Reservation with details
    public Reservation(String guestName, int numGuests, LocalDate arrivalDate, LocalDate departureDate) {
        this.guestName = guestName;
        this.numGuests = numGuests;
        this.arrivalDate = arrivalDate;
        this.departureDate = departureDate;
    }

    // Get the guest's name for this reservation
    public String getGuestName() {
        return guestName;
    }

    // Get the number of guests in this reservation
    public int getNumGuests() {
        return numGuests;
    }

    // Get the arrival date for this reservation
    public LocalDate getArrivalDate() {
        return arrivalDate;
    }

    // Get the departure date for this reservation
    public LocalDate getDepartureDate() {
        return departureDate;
    }

    // Get the Hotel linked to this reservation (might be null initially)
    public Hotel getHotel() {
        return hotel;
    }

    // Set the Hotel this reservation is for
    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    // Get the Room assigned to this reservation (might be null initially)
    public Room getRoom() {
        return room;
    }

    // Set the Room assigned to this reservation
    public void setRoom(Room room) {
        this.room = room;
    }

    // Override the default toString method to provide a clear description of the reservation
    @Override
    public String toString() {
        // Include room information only if a room is assigned
        String roomInfo = room != null ? ", Room: " + room : "";
        return "Reservation for " + guestName + ": " + numGuests + " guests, arriving " + arrivalDate +
                " and departing " + departureDate + roomInfo;
    }
}
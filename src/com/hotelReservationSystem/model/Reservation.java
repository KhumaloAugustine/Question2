package com.hotelReservationSystem.model;

import java.time.LocalDate;

public class Reservation {

    private final String guestName;
    private final int numGuests;
    private final LocalDate arrivalDate;
    private final LocalDate departureDate;
    private Hotel hotel;
    private Room room;

    public Reservation(String guestName, int numGuests, LocalDate arrivalDate, LocalDate departureDate) {
        this.guestName = guestName;
        this.numGuests = numGuests;
        this.arrivalDate = arrivalDate;
        this.departureDate = departureDate;
    }

    public String getGuestName() {
        return guestName;
    }

    public int getNumGuests() {
        return numGuests;
    }

    public LocalDate getArrivalDate() {
        return arrivalDate;
    }

    public LocalDate getDepartureDate() {
        return departureDate;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    @Override
    public String toString() {
        return "Reservation for " + guestName + ": " + numGuests + " guests, arriving " + arrivalDate +
                " and departing " + departureDate + (room != null ? ", Room: " + room : "");
    }
}
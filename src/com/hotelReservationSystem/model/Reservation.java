package com.hotelReservationSystem.model;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Reservation {
    private String guestName;
    private int numGuests;
    private LocalDate arrivalDate;
    private LocalDate departureDate;
    private String hotelName;
    private int roomNumber;

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

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public long getNumNights() {
        return ChronoUnit.DAYS.between(arrivalDate, departureDate);
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    public double getTotalPrice() {
        // Calculate total price based on hotel and room prices and number of nights
        // This would depend on the actual implementation
        return 0.0;
    }
}

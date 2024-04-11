package com.hotelReservationSystem.service;

import com.hotelReservationSystem.model.Hotel;
import com.hotelReservationSystem.model.Reservation;
import com.hotelReservationSystem.model.Room;

import java.util.List;

public interface HotelService {

    /**
     * This method searches for hotels that have available rooms based on a reservation's details.
     * It considers factors like arrival date, departure date, and number of guests
     * to find suitable hotels.
     *
     * @param reservation The reservation containing details about the stay (dates, guests)
     * @return A list of hotels that have rooms available for the given reservation
     */
    List<Hotel> findAvailableHotels(Reservation reservation);

    /**
     * This method attempts to book a specific room in a chosen hotel for a reservation.
     * It will only succeed if the room is available for the reservation's dates.
     *
     * @param reservation The reservation containing guest details and desired stay
     * @param hotel The hotel where the room should be booked
     * @param room The specific room to be booked within the chosen hotel
     * @return True if the room was booked successfully, False otherwise (e.g., room unavailable)
     */
    boolean bookRoom(Reservation reservation, Hotel hotel, Room room);
    // New method to retrieve reservations by guest name
    List<Reservation> getReservationsByGuestName(String guestName);

    // New method to cancel a reservation
    boolean cancelReservation(Reservation reservation);
}
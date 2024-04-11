package com.hotelReservationSystem.service;

import com.hotelReservationSystem.model.Hotel;
import com.hotelReservationSystem.model.Reservation;
import com.hotelReservationSystem.model.Room;

import java.util.List;

public interface HotelService {

    List<Hotel> findAvailableHotels(Reservation reservation);

    boolean bookRoom(Reservation reservation, Hotel hotel, Room room);
}
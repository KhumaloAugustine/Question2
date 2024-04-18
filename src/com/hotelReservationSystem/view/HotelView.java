package com.hotelReservationSystem.view;

import com.hotelReservationSystem.model.Hotel;
import com.hotelReservationSystem.model.Reservation;
import com.hotelReservationSystem.model.Room;

import java.util.List;

public class HotelView {

    public void displayWelcomeMessage() {
        System.out.println("Welcome to the Hotel Reservation System!");
    }

    public void displayMainMenu() {
        System.out.println("\nMain Menu:");
        System.out.println("1. Make a Reservation");
        System.out.println("2. View Reservations");
        System.out.println("3. Cancel Reservation");
        System.out.println("4. Exit");
    }

    public void displayErrorMessage(String message) {
        System.out.println("Error: " + message);
    }

    public void displayAvailableHotels(List<Hotel> hotels) {
        System.out.println("\nAvailable Hotels:");
        for (int i = 0; i < hotels.size(); i++) {
            Hotel hotel = hotels.get(i);
            System.out.println((i + 1) + ". " + hotel.getName());
            System.out.println("   Location: " + hotel.getLocation());
            System.out.println("   Rating: " + hotel.getRating());
            System.out.println("   Price Range: " + hotel.getPriceRange());
        }
    }

    public void displayAvailableRooms(List<Room> rooms) {
        System.out.println("\nAvailable Rooms:");
        for (int i = 0; i < rooms.size(); i++) {
            Room room = rooms.get(i);
            System.out.println((i + 1) + ". Room " + room.getNumber() + " - " + room.getType());
            System.out.println("   Price per Night: $" + room.getPricePerNight());
            System.out.println("   Capacity: " + room.getCapacity() + " guests");
        }
    }

    public void displayReservationConfirmation(Reservation reservation, Hotel hotel, Room room) {
        System.out.println("\nReservation Confirmation:");
        System.out.println("Guest Name: " + reservation.getGuestName());
        System.out.println("Hotel: " + hotel.getName());
        System.out.println("Room: " + room.getNumber() + " - " + room.getType());
        System.out.println("Arrival Date: " + reservation.getArrivalDate());
        System.out.println("Departure Date: " + reservation.getDepartureDate());
        System.out.println("Total Price: $" + room.getPricePerNight() * reservation.getNumNights());
    }

    public void displayReservations(List<Reservation> reservations) {
        System.out.println("\nYour Reservations:");
        for (int i = 0; i < reservations.size(); i++) {
            Reservation reservation = reservations.get(i);
            System.out.println((i + 1) + ". Guest Name: " + reservation.getGuestName());
            System.out.println("   Hotel: " + reservation.getHotelName());
            System.out.println("   Room: " + reservation.getRoomNumber());
            System.out.println("   Arrival Date: " + reservation.getArrivalDate());
            System.out.println("   Departure Date: " + reservation.getDepartureDate());
            System.out.println("   Total Price: $" + reservation.getTotalPrice());
        }
    }

    public void displayCancellationConfirmation() {
        System.out.println("Reservation successfully canceled.");
    }
}
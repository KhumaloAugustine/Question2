package com.hotelReservationSystem.view;

import com.hotelReservationSystem.model.Hotel;
import com.hotelReservationSystem.model.Reservation;
import com.hotelReservationSystem.model.Room;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class HotelView {

    private final Scanner scanner;

    public HotelView() {
        this.scanner = new Scanner(System.in);
    }

    public void displayWelcomeMessage() {
        System.out.println("\nWelcome to the Hotel Reservation System!");
    }

    public String getGuestName() {
        System.out.print("Enter your name: ");
        return scanner.nextLine().trim();
    }

    public int getNumGuests() {
        System.out.print("Enter the number of guests: ");
        return scanner.nextInt();
    }

    public LocalDate getArrivalDate() {
        System.out.print("Enter arrival date (YYYY-MM-DD): ");
        String dateStr = scanner.nextLine().trim();
        return LocalDate.parse(dateStr);
    }

    public LocalDate getDepartureDate() {
        System.out.print("Enter departure date (YYYY-MM-DD): ");
        String dateStr = scanner.nextLine().trim();
        return LocalDate.parse(dateStr);
    }

    public void displayAvailableHotels(List<Hotel> hotels) {
        System.out.println("\nAvailable Hotels:");
        int counter = 1;
        for (Hotel hotel : hotels) {
            System.out.println(counter + ". " + hotel.getName());
            counter++;
        }
    }

    public int chooseHotel(int numHotels) {
        System.out.print("Enter the number of the hotel you want to choose (1-" + numHotels + "): ");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline character
        return choice - 1; // Adjust for zero-based indexing
    }

    public void displayAvailableRooms(List<Room> rooms) {
        System.out.println("\nAvailable Rooms:");
        if (rooms.isEmpty()) {
            System.out.println("  No rooms available for your selected dates.");
        } else {
            for (Room room : rooms) {
                System.out.println("  " + room);
            }
        }
    }

    public int chooseRoom(int numRooms) {
        System.out.print("Enter the number of the room you want to book (1-" + numRooms + "): ");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline character
        return choice - 1; // Adjust for zero-based indexing
    }

    public void displayConfirmation(Reservation reservation, Room room) {
        System.out.println("\nReservation confirmed!");
        System.out.println("Guest Name: " + reservation.getGuestName());
        System.out.println("Hotel: " + reservation.getHotel().getName());
        System.out.println("Room: " + room);
        System.out.println("Arrival Date: " + reservation.getArrivalDate());
        System.out.println("Departure Date: " + reservation.getDepartureDate());
    }

    public void displayErrorMessage(String message) {
        System.out.println("\nError: " + message);
    }
}
package com.hotelReservationSystem.view;

import com.hotelReservationSystem.model.Hotel;
import com.hotelReservationSystem.model.Reservation;
import com.hotelReservationSystem.model.Room;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class HotelView {

    // Object to read user input from the console
    private final Scanner scanner;

    public HotelView() {
        // Create a Scanner object to get user input
        this.scanner = new Scanner(System.in);
    }

    // Display a welcome message to the user
    public void displayWelcomeMessage() {
        System.out.println("\nWelcome to the Hotel Reservation System!");
    }

    // Get the guest's name from the user
    public String getGuestName() {
        System.out.print("Enter your name: ");
        return scanner.nextLine().trim(); // Remove leading/trailing spaces
    }

    // Get the number of guests from the user
    public int getNumGuests() {
        System.out.print("Enter the number of guests: ");
        return scanner.nextInt();
    }

    // Get the arrival date from the user in YYYY-MM-DD format
    public LocalDate getArrivalDate() {
        System.out.print("Enter arrival date (YYYY-MM-DD): ");
        String dateStr = scanner.nextLine().trim();
        return LocalDate.parse(dateStr); // Convert the string to a date object
    }

    // Get the departure date from the user in YYYY-MM-DD format
    public LocalDate getDepartureDate() {
        System.out.print("Enter departure date (YYYY-MM-DD): ");
        String dateStr = scanner.nextLine().trim();
        return LocalDate.parse(dateStr); // Convert the string to a date object
    }

    // Display a list of available hotels to the user
    public void displayAvailableHotels(List<Hotel> hotels) {
        System.out.println("\nAvailable Hotels:");
        int counter = 1; // Keep track of hotel number for display
        for (Hotel hotel : hotels) {
            System.out.println(counter + ". " + hotel.getName());
            counter++;
        }
    }

    // Get the user's choice of hotel from a numbered list
    public int chooseHotel(int numHotels) {
        System.out.print("Enter the number of the hotel you want to choose (1-" + numHotels + "): ");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline character after integer input
        // Adjust user choice for zero-based indexing (common in programming)
        return choice - 1;
    }

    // Display a list of available rooms to the user
    public void displayAvailableRooms(List<Room> rooms) {
        System.out.println("\nAvailable Rooms:");
        if (rooms.isEmpty()) {
            System.out.println("  No rooms available for your selected dates.");
        } else {
            for (Room room : rooms) {
                System.out.println("  " + room); // Use room's toString() method for description
            }
        }
    }

    // Get the user's choice of room from a numbered list
    public int chooseRoom(int numRooms) {
        System.out.print("Enter the number of the room you want to book (1-" + numRooms + "): ");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline character after integer input
        // Adjust user choice for zero-based indexing (common in programming)
        return choice - 1;
    }

    // Display a confirmation message with reservation details
    public void displayConfirmation(Reservation reservation, Room room) {
        System.out.println("\nReservation confirmed!");
        System.out.println("Guest Name: " + reservation.getGuestName());
        System.out.println("Hotel: " + reservation.getHotel().getName());
        System.out.println("Room: " + room);
        System.out.println("Arrival Date: " + reservation.getArrivalDate());
        System.out.println("Departure Date: " + reservation.getDepartureDate());
    }

    // Display an error message to the user
    public void displayErrorMessage(String message) {
        System.out.println("\nError: " + message);
    }
}
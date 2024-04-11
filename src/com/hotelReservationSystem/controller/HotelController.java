package com.hotelReservationSystem.controller;

import com.hotelReservationSystem.model.Hotel;
import com.hotelReservationSystem.model.Reservation;
import com.hotelReservationSystem.model.Room;
import com.hotelReservationSystem.service.HotelService;
import com.hotelReservationSystem.view.HotelView;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

public class HotelController {

    private final HotelService hotelService;
    private final HotelView view;
    private final Scanner scanner;

    public HotelController(HotelService hotelService, HotelView view) {
        this.hotelService = hotelService;
        this.view = view;
        this.scanner = new Scanner(System.in);
    }

    public void run() {
        view.displayWelcomeMessage();

        int choice;
        do {
            view.displayMainMenu();
            choice = getIntegerInput("Enter your choice: ");

            switch (choice) {
                case 1:
                    makeReservation();
                    break;
                case 2:
                    viewReservations();
                    break;
                case 3:
                    cancelReservation();
                    break;
                case 4:
                    System.out.println("\nThank you for using the Hotel Reservation System!");
                    break;
                default:
                    view.displayErrorMessage("Invalid choice. Please try again.");
            }
        } while (choice != 4);
    }

    private int getIntegerInput(String prompt) {
        int choice;
        do {
            System.out.print(prompt);
            while (!scanner.hasNextInt()) {
                scanner.next(); // Clear invalid input
                System.out.println("Please enter a valid number.");
            }
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline character
        } while (choice < 1 || choice > 4); // Validate menu choice
        return choice;
    }

    private String getStringInput(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }

    private LocalDate getValidDateInput(String prompt) {
        LocalDate date;
        do {
            System.out.print(prompt + " (YYYY-MM-DD): ");
            String dateStr = scanner.nextLine().trim();
            try {
                date = LocalDate.parse(dateStr);
            } catch (DateTimeParseException e) {
                System.out.println("Invalid date format. Please try again.");
                date = null;
            }
        } while (date == null);
        return date;
    }

    private void makeReservation() {
        String guestName = getStringInput("Enter your name: ");
        int numGuests = getIntegerInput("Enter the number of guests: ");

        LocalDate arrivalDate = getValidDateInput("Enter arrival date: ");
        LocalDate departureDate = getValidDateInput("Enter departure date: ");

        if (departureDate.isBefore(arrivalDate)) {
            System.out.println("Departure date cannot be before arrival date. Please try again.");
            return;
        }

        Reservation reservation = new Reservation(guestName, numGuests, arrivalDate, departureDate);

        List<Hotel> availableHotels = hotelService.findAvailableHotels(reservation);
        if (availableHotels.isEmpty()) {
            view.displayErrorMessage("No hotels available for your selected dates.");
        } else {
            view.displayAvailableHotels(availableHotels);
            int chosenHotelIndex = getIntegerInput("Enter the number of the hotel you want to choose (1-" + availableHotels.size() + "): ") - 1;

            Hotel chosenHotel = availableHotels.get(chosenHotelIndex);

            // Set the chosen hotel to the reservation
            reservation.setHotel(chosenHotel);

            List<Room> availableRooms = chosenHotel.findAvailableRooms(reservation);
            if (availableRooms.isEmpty()) {
                view.displayErrorMessage("No rooms available at the chosen hotel for your selected dates.");
            } else {
                view.displayAvailableRooms(availableRooms);
                int chosenRoomIndex = getIntegerInput("Enter the number of the room you want to book (1-" + availableRooms.size() + "): ") - 1;

                Room chosenRoom = availableRooms.get(chosenRoomIndex);

                if (hotelService.bookRoom(reservation, chosenHotel, chosenRoom)) {
                    view.displayConfirmation(reservation, chosenRoom);
                } else {
                    view.displayErrorMessage("An error occurred while booking the room.");
                }
            }
        }
    }

    private void viewReservations() {
        // Get the guest's name for whom reservations should be viewed
        String guestName = getStringInput("Enter your name: ");

        // Implement logic to retrieve existing reservations for the given guest name
        // This could involve querying a database or other data storage mechanism
        // For now, let's assume we have a method in HotelService to retrieve reservations by guest name
        List<Reservation> reservations = hotelService.getReservationsByGuestName(guestName);

        if (reservations.isEmpty()) {
            view.displayErrorMessage("No reservations found for " + guestName);
        } else {
            // Display each reservation to the user
            for (Reservation reservation : reservations) {
                view.displayReservation(reservation);
            }
        }
    }


    private void cancelReservation() {
        // Get the guest's name for whom reservations should be canceled
        String guestName = getStringInput("Enter your name: ");

        // Implement logic to retrieve existing reservations for the given guest name
        // This could involve querying a database or other data storage mechanism
        // For now, let's assume we have a method in HotelService to retrieve reservations by guest name
        List<Reservation> reservations = hotelService.getReservationsByGuestName(guestName);

        if (reservations.isEmpty()) {
            view.displayErrorMessage("No reservations found for " + guestName);
        } else {
            // Display each reservation to the user
            for (int i = 0; i < reservations.size(); i++) {
                Reservation reservation = reservations.get(i);
                view.displayReservation(reservation);
                int choice = getIntegerInput("Enter the number of the reservation you want to cancel (1-" + reservations.size() + "), or 0 to cancel: ");
                if (choice == 0) {
                    return; // User canceled operation
                } else if (choice > 0 && choice <= reservations.size()) {
                    // Get the selected reservation
                    Reservation selectedReservation = reservations.get(choice - 1);
                    // Call HotelService method to cancel the reservation
                    boolean canceled = hotelService.cancelReservation(selectedReservation);
                    if (canceled) {
// Call the method to display cancellation confirmation in your controller
                        view.displayCancellationConfirmation();
                    } else {
                        view.displayErrorMessage("Failed to cancel reservation.");
                    }
                    return; // Operation completed
                } else {
                    view.displayErrorMessage("Invalid choice. Please try again.");
                }
            }
        }
    }

}
package com.hotelReservationSystem.controller;

import com.hotelReservationSystem.model.Hotel;
import com.hotelReservationSystem.model.Reservation;
import com.hotelReservationSystem.model.Room;
import com.hotelReservationSystem.service.HotelService;
import com.hotelReservationSystem.view.HotelView;

import java.time.LocalDate;
import java.util.List;

public class HotelController {

    // Interface for interacting with the hotel reservation service
    private final HotelService hotelService;

    // Object for displaying information and getting user input
    private final HotelView view;

    public HotelController(HotelService hotelService, HotelView view) {
        this.hotelService = hotelService;
        this.view = view;
    }

    // Starts the main loop of the reservation system
    public void run() {
        // Welcome message displayed at the beginning
        view.displayWelcomeMessage();

        int choice;
        // Loop continues until user exits (choice 4)
        do {
            choice = displayMainMenu();
            switch (choice) {
                case 1:
                    makeReservation();
                    break;
                case 2:
                    // Feature not implemented yet, display error message
                    view.displayErrorMessage("Not implemented yet (View Reservations)");
                    break;
                case 3:
                    // Feature not implemented yet, display error message
                    view.displayErrorMessage("Not implemented yet (Cancel Reservation)");
                    break;
                case 4:
                    System.out.println("\nThank you for using the Hotel Reservation System!");
                    break;
                default:
                    view.displayErrorMessage("Invalid choice. Please try again.");
            }
        } while (choice != 4);
    }

    // Displays the main menu and gets user choice
    private int displayMainMenu() {
        System.out.println("\nMain Menu:");
        System.out.println("1. Make a Reservation");
        System.out.println("2. View Reservations (Not implemented)");
        System.out.println("3. Cancel Reservation (Not implemented)");
        System.out.println("4. Exit");
        System.out.print("Enter your choice: ");
        // Reusing scanner input for menu choice (can be improved for better separation)
        return view.getNumGuests();
    }

    // Handles the process of making a reservation
    private void makeReservation() {
        String guestName = view.getGuestName();
        int numGuests = view.getNumGuests();
        LocalDate arrivalDate = view.getArrivalDate();
        LocalDate departureDate = view.getDepartureDate();

        // Create a new reservation object with user-provided details
        Reservation reservation = new Reservation(guestName, numGuests, arrivalDate, departureDate);

        // Find available hotels based on the reservation requirements
        List<Hotel> availableHotels = hotelService.findAvailableHotels(reservation);
        if (availableHotels.isEmpty()) {
            view.displayErrorMessage("No hotels available for your selected dates.");
        } else {
            // Display available hotels to the user
            view.displayAvailableHotels(availableHotels);
            int chosenHotelIndex = view.chooseHotel(availableHotels.size());
            Hotel chosenHotel = availableHotels.get(chosenHotelIndex);

            // Find available rooms at the chosen hotel for the reservation dates
            List<Room> availableRooms = chosenHotel.findAvailableRooms(reservation);
            if (availableRooms.isEmpty()) {
                view.displayErrorMessage("No rooms available at the chosen hotel for your selected dates.");
            } else {
                // Display available rooms to the user
                view.displayAvailableRooms(availableRooms);
                int chosenRoomIndex = view.chooseRoom(availableRooms.size());
                Room chosenRoom = availableRooms.get(chosenRoomIndex);

                // Attempt to book the chosen room
                if (hotelService.bookRoom(reservation, chosenHotel, chosenRoom)) {
                    view.displayConfirmation(reservation, chosenRoom);
                } else {
                    view.displayErrorMessage("An error occurred while booking the room.");
                }
            }
        }
    }
}
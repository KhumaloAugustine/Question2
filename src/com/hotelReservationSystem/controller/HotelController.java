package com.hotelReservationSystem.controller;

import com.hotelReservationSystem.model.Hotel;
import com.hotelReservationSystem.model.Reservation;
import com.hotelReservationSystem.model.Room;
import com.hotelReservationSystem.service.HotelService;
import com.hotelReservationSystem.view.HotelView;

import java.time.LocalDate;
import java.util.List;

public class HotelController {

    private final HotelService hotelService;
    private final HotelView view;

    public HotelController(HotelService hotelService, HotelView view) {
        this.hotelService = hotelService;
        this.view = view;
    }

    public void run() {
        view.displayWelcomeMessage();
        int choice;
        do {
            choice = displayMainMenu();
            switch (choice) {
                case 1:
                    makeReservation();
                    break;
                case 2:
                    view.displayErrorMessage("Not implemented yet (View Reservations)");
                    break;
                case 3:
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

    private int displayMainMenu() {
        System.out.println("\nMain Menu:");
        System.out.println("1. Make a Reservation");
        System.out.println("2. View Reservations (Not implemented)");
        System.out.println("3. Cancel Reservation (Not implemented)");
        System.out.println("4. Exit");
        System.out.print("Enter your choice: ");
        return view.getNumGuests(); // Scanner input reused for menu choice (can be improved)
    }

    private void makeReservation() {
        String guestName = view.getGuestName();
        int numGuests = view.getNumGuests();
        LocalDate arrivalDate = view.getArrivalDate();
        LocalDate departureDate = view.getDepartureDate();

        Reservation reservation = new Reservation(guestName, numGuests, arrivalDate, departureDate);

        List<Hotel> availableHotels = hotelService.findAvailableHotels(reservation);
        if (availableHotels.isEmpty()) {
            view.displayErrorMessage("No hotels available for your selected dates.");
        } else {
            view.displayAvailableHotels(availableHotels);
            int chosenHotelIndex = view.chooseHotel(availableHotels.size());
            Hotel chosenHotel = availableHotels.get(chosenHotelIndex);

            List<Room> availableRooms = chosenHotel.findAvailableRooms(reservation);
            if (availableRooms.isEmpty()) {
                view.displayErrorMessage("No rooms available at the chosen hotel for your selected dates.");
            } else {
                view.displayAvailableRooms(availableRooms);
                int chosenRoomIndex = view.chooseRoom(availableRooms.size());
                Room chosenRoom = availableRooms.get(chosenRoomIndex);

                if (hotelService.bookRoom(reservation, chosenHotel, chosenRoom)) {
                    view.displayConfirmation(reservation, chosenRoom);
                } else {
                    view.displayErrorMessage("An error occurred while booking the room.");
                }
            }
        }
    }
}
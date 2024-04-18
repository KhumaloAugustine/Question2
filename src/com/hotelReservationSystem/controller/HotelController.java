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
                scanner.next();
                System.out.println("Please enter a valid number.");
            }
            choice = scanner.nextInt();
            scanner.nextLine();
        } while (choice < 1 || choice > 4);
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
        LocalDate arrivalDate = getValidDateInput("Enter arrival date");
        LocalDate departureDate = getValidDateInput("Enter departure date");

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
            int chosenHotelIndex = getIntegerInput("Choose a hotel (1-" + availableHotels.size() + "): ");
            if (chosenHotelIndex < 1 || chosenHotelIndex > availableHotels.size()) {
                view.displayErrorMessage("Invalid choice. Please try again.");
            } else {
                Hotel chosenHotel = availableHotels.get(chosenHotelIndex - 1);
                List<Room> availableRooms = chosenHotel.findAvailableRooms(reservation);
                if (availableRooms.isEmpty()) {
                    view.displayErrorMessage("No available rooms in the chosen hotel.");
                } else {
                    view.displayAvailableRooms(availableRooms);
                    int chosenRoomIndex = getIntegerInput("Choose a room (1-" + availableRooms.size() + "): ");
                    if (chosenRoomIndex < 1 || chosenRoomIndex > availableRooms.size()) {
                        view.displayErrorMessage("Invalid choice. Please try again.");
                    } else {
                        Room chosenRoom = availableRooms.get(chosenRoomIndex - 1);
                        boolean success = hotelService.bookRoom(reservation, chosenHotel, chosenRoom);
                        if (success) {
                            view.displayReservationConfirmation(reservation, chosenHotel, chosenRoom);
                        } else {
                            view.displayErrorMessage("Failed to make reservation.");
                        }
                    }
                }
            }
        }
    }

    private void viewReservations() {
        String guestName = getStringInput("Enter your name: ");
        List<Reservation> reservations = hotelService.getReservationsByGuestName(guestName);
        if (reservations.isEmpty()) {
            view.displayErrorMessage("No reservations found for " + guestName);
        } else {
            view.displayReservations(reservations);
        }
    }

    private void cancelReservation() {
        String guestName = getStringInput("Enter your name: ");
        List<Reservation> reservations = hotelService.getReservationsByGuestName(guestName);
        if (reservations.isEmpty()) {
            view.displayErrorMessage("No reservations found for " + guestName);
        } else {
            view.displayReservations(reservations);
            int choice = getIntegerInput("Enter the number of the reservation you want to cancel (1-" + reservations.size() + "), or 0 to cancel: ");
            if (choice == 0) {
                return;
            } else if (choice > 0 && choice <= reservations.size()) {
                Reservation selectedReservation = reservations.get(choice - 1);
                boolean canceled = hotelService.cancelReservation(selectedReservation);
                if (canceled) {
                    view.displayCancellationConfirmation();
                } else {
                    view.displayErrorMessage("Failed to cancel reservation.");
                }
            } else {
                view.displayErrorMessage("Invalid choice. Please try again.");
            }
        }
    }
}
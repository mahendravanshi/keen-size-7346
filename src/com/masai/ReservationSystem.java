package com.masai;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class ReservationSystem implements Serializable {
    private List<Bus> buses;
    private transient Scanner scanner; // Transient: scanner should not be serialized

    public ReservationSystem() {
        this.buses = new ArrayList<>();
        this.scanner = new Scanner(System.in);
    }

    public void addBus(Bus bus) {
        buses.add(bus);
    }

    public void displayBusList() {
        System.out.println("Available Buses:");
       
        for (Bus bus : buses) {
            System.out.println(bus.getBusNumber() + " - Available Seats: " + bus.getAvailableSeats());
        }
    }

    public void bookTicket() {
        System.out.print("Enter bus number: ");
        String busNumber = scanner.nextLine();

        Bus selectedBus = null;
        for (Bus bus : buses) {
            if (bus.getBusNumber().equalsIgnoreCase(busNumber)) {
                selectedBus = bus;
                break;
            }
        }

        if (selectedBus == null) {
            System.out.println("Bus not found!");
            return;
        }

        System.out.println("Bus: " + selectedBus.getBusNumber() + " - Available Seats: " + selectedBus.getAvailableSeats());
        System.out.print("Enter seat number: ");
        int seatNumber = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        try {
            boolean success = selectedBus.bookSeat(seatNumber);
            if (success) {
                System.out.println("Seat " + seatNumber + " booked successfully!");
            } else {
                System.out.println("Failed to book seat " + seatNumber + ".");
            }
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        } catch (IllegalStateException e) {
            System.out.println(e.getMessage());
        }
    }

    // Serialization method
    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
    }

    // Deserialization method
    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        scanner = new Scanner(System.in); // Re-initialize transient field
    }
}


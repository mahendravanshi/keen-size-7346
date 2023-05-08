package com.masai;

import java.io.*;
import java.util.Scanner;

class Admin implements Serializable {
    private transient ReservationSystem reservationSystem;
    private transient Scanner scanner;

    public Admin(ReservationSystem reservationSystem) {
        this.reservationSystem = reservationSystem;
        this.scanner = new Scanner(System.in);
    }

    public void menu() {
        int choice;
        do {
            System.out.println("\nAdmin Menu");
            System.out.println("1. Add Bus");
            System.out.println("2. View Bus List");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    addBus();
                    break;
                case 2:
                    viewBusList();
                    break;
                case 3:
                    System.out.println("Exiting Admin Menu...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        } while (choice != 3);
    }

    private void addBus() {
        System.out.print("Enter bus number: ");
        String busNumber = scanner.nextLine();
        System.out.print("Enter total seats: ");
        int totalSeats = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        Bus newBus = new Bus(busNumber, totalSeats);
        reservationSystem.addBus(newBus);
//        reservationSystem.displayBusList();
        Main.saveReservationSystem(reservationSystem);
        System.out.println("Bus " + busNumber + " with " + totalSeats + " seats added successfully!");
    }

    private void viewBusList() {
        reservationSystem.displayBusList();
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

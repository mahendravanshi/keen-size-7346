package com.masai;

import java.io.*;
import java.util.Scanner;

class Customer implements Serializable {
    private transient ReservationSystem reservationSystem;
    private transient Scanner scanner;
    
    
    private String username;

//    public Customer(String username) {
//        this.username = username;
//    }

    public String getUsername() {
        return username;
    }

    public Customer(ReservationSystem reservationSystem) {
        this.reservationSystem = reservationSystem;
        this.scanner = new Scanner(System.in);
    }
    
    
    public void menu(Scanner sc){
        int choice;
        do {
            System.out.println("\nCustomer Menu");
            System.out.println("1. View Bus List");
            System.out.println("2. Book Ticket");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            sc.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    viewBusList();
                    break;
                case 2:
                    bookTicket();
                    break;
                case 3:
                    System.out.println("Exiting Customer Menu...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        } while (choice != 3);
    }

    

    private void viewBusList() {
        reservationSystem.displayBusList();
    }

    private void bookTicket() {
        reservationSystem.bookTicket();
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

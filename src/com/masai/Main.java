
package com.masai;

import java.io.*;
import java.util.Scanner;

public class Main {
    private static final String RESERVATION_SYSTEM_FILE = "reservation_system.ser";
    private static final String CUSTOMER_AUTH_FILE = "customers.ser";
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws AdminLoginException {
        ReservationSystem reservationSystem = loadReservationSystem();
//        reservationSystem.displayBusList();
        if (reservationSystem == null) {
            reservationSystem = new ReservationSystem();
        }

        CustomerAuthentication customerAuthentication = loadCustomerAuthentication();

        int choice;
        do {
            System.out.println("\nWelcome to Bus Ticket Reservation System");
            System.out.println("1. Admin Login");
            System.out.println("2. Customer Login");
            System.out.println("3. Customer Signup");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    Admin admin = loginAsAdmin();
                    if (admin != null) {
                        admin.menu();
//                        saveReservationSystem(reservationSystem);
                    }
                    else {
                    	throw new AdminLoginException("Please enter correct details");
                    }
                    
                    break;
                case 2:
                    Customer customer = loginAsCustomer(customerAuthentication,scanner);
                    if (customer != null) {
                    	customer.menu(scanner);
                    	saveReservationSystem(reservationSystem);
                    	
                    }
                    break;
                case 3:
                    signupAsCustomer(customerAuthentication);
                    saveCustomerAuthentication(customerAuthentication);
                    break;
                case 4:
                    System.out.println("Exiting Bus Ticket Reservation System...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        } while (choice != 4);

        saveReservationSystem(reservationSystem);
        saveCustomerAuthentication(customerAuthentication);
    }

    

	private static ReservationSystem loadReservationSystem() {
        ReservationSystem reservationSystem = null;

        try (FileInputStream fileIn = new FileInputStream(RESERVATION_SYSTEM_FILE);
             ObjectInputStream in = new ObjectInputStream(fileIn)) {

            reservationSystem = (ReservationSystem) in.readObject();
            System.out.println("Reservation system loaded successfully.");

        } catch (FileNotFoundException e) {
            System.out.println("Reservation system file not found. Creating a new system.");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading reservation system. Creating a new system.");
        }

        return reservationSystem;
    }

    public static void saveReservationSystem(ReservationSystem reservationSystem) {
    	
    	
        try (FileOutputStream fileOut = new FileOutputStream(RESERVATION_SYSTEM_FILE);
             ObjectOutputStream out = new ObjectOutputStream(fileOut)) {
            
            out.writeObject(reservationSystem);
            out.flush();
            System.out.println("Reservation system saved successfully.");

        } catch (IOException e) {
            System.out.println("Error saving reservation system.");
        }
    }
    
    
    
    private static CustomerAuthentication loadCustomerAuthentication() {
        CustomerAuthentication customerAuthentication = null;

        try (FileInputStream fileIn = new FileInputStream(CUSTOMER_AUTH_FILE);
             ObjectInputStream in = new ObjectInputStream(fileIn)) {

            customerAuthentication = (CustomerAuthentication) in.readObject();
            System.out.println("Customer authentication data loaded successfully.");

        } catch (FileNotFoundException e) {
            System.out.println("Customer authentication data file not found. Creating a new data file.");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading customer authentication data. Creating a new data file.");
        }

        if (customerAuthentication == null) {
            customerAuthentication = new CustomerAuthentication();
        }

        return customerAuthentication;
    }
    
    private static Customer loginAsCustomer(CustomerAuthentication customerAuthentication,Scanner scanner) {
        System.out.println("\nCustomer Login");
        
        System.out.print("Username: ");
        String username = scanner.nextLine();
        System.out.print("Password: ");
        String password = scanner.nextLine();

        // Perform authentication logic
        if (customerAuthentication.authenticateCustomer(username, password)) {
        	
        	 ReservationSystem reservationSystem = loadReservationSystem();
             if (reservationSystem == null) {
                 reservationSystem = new ReservationSystem();
             }
            return new Customer(reservationSystem);
        } else {
            System.out.println("Invalid credentials. Customer login failed.");
            return null;
        }
    }

    private static void signupAsCustomer(CustomerAuthentication customerAuthentication) {
    	System.out.println("\nCustomer Signup");
    	System.out.print("Username: ");
    	String username = scanner.nextLine();
    	System.out.print("Password: ");
    	String password = scanner.nextLine();


        // Check if the username is already taken
        if (customerAuthentication.isUsernameTaken(username)) {
            System.out.println("Username already taken. Please choose a different username.");
        } else {
            // Add the new customer to the authentication system
            customerAuthentication.addCustomer(username, password);
            System.out.println("Customer signup successful.");
        }
    }

    
    
    private static void saveCustomerAuthentication(CustomerAuthentication customerAuthentication) {
        try (FileOutputStream fileOut = new FileOutputStream(CUSTOMER_AUTH_FILE);
             ObjectOutputStream out = new ObjectOutputStream(fileOut)) {

            out.writeObject(customerAuthentication);
            System.out.println("Customer authentication data saved successfully.");

        } catch (IOException e) {
            System.out.println("Error saving customer authentication data.");
        }
    }


    private static Admin loginAsAdmin() {
    	System.out.println("\nAdmin Login");
        System.out.print("Username: ");
        String username = scanner.nextLine();
        System.out.print("Password: ");
        String password = scanner.nextLine();

        // Perform authentication logic
        if (authenticateAdmin(username, password)) {
            ReservationSystem reservationSystem = loadReservationSystem();
//            reservationSystem.displayBusList();
            if (reservationSystem == null) {
                reservationSystem = new ReservationSystem();
            }
            return new Admin(reservationSystem);
        } else {
            System.out.println("Invalid credentials. Admin login failed.");
            return null;
        }
    }
    
    private static boolean authenticateAdmin(String username, String password) {
        
        String adminUsername = "admin";
        String adminPassword = "admin";

        return username.equals(adminUsername) && password.equals(adminPassword);
    }


    
}

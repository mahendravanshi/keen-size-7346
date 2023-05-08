package com.masai;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

class Bus implements Serializable {
    private String busNumber;
    private int totalSeats;
    private int availableSeats;
    private Map<Integer, Boolean> seatMap;

    public Bus(String busNumber, int totalSeats) {
        this.busNumber = busNumber;
        this.totalSeats = totalSeats;
        this.availableSeats = totalSeats;
        this.seatMap = new HashMap<>();
        for (int i = 1; i <= totalSeats; i++) {
            seatMap.put(i, true); // true indicates seat is available
        }
    }

    public String getBusNumber() {
        return busNumber;
    }

    public int getTotalSeats() {
        return totalSeats;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }

    public boolean bookSeat(int seatNumber) {
        if (seatNumber < 1 || seatNumber > totalSeats) {
            throw new IllegalArgumentException("Invalid seat number!");
        }

        if (seatMap.get(seatNumber)) {
            seatMap.put(seatNumber, false); // false indicates seat is booked
            availableSeats--;
            return true;
        } else {
            throw new IllegalStateException("Seat " + seatNumber + " is already booked!");
        }
    }

    // Serialization method
    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
    }

    // Deserialization method
    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
    }
}

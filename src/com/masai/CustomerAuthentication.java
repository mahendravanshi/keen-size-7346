package com.masai;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

class CustomerAuthentication implements Serializable {
	
    private Map<String, String> customers;

    public CustomerAuthentication() {
        this.customers = new HashMap<>();
    }

    public void addCustomer(String username, String password) {
        customers.put(username, password);
    }

    public boolean authenticateCustomer(String username, String password) {
        String storedPassword = customers.get(username);
        return storedPassword != null && storedPassword.equals(password);
    }

    public boolean isUsernameTaken(String username) {
        return customers.containsKey(username);
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


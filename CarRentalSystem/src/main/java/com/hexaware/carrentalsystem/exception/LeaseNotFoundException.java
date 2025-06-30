package com.hexaware.carrentalsystem.exception;

public class LeaseNotFoundException extends Exception {
    
    public LeaseNotFoundException() {
        super("Lease not found.\n");
    }
    
    public LeaseNotFoundException(String message) {
        super(message);
    }
}

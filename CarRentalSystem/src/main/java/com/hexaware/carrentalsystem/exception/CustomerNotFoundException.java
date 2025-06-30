package com.hexaware.carrentalsystem.exception;

public class CustomerNotFoundException extends Exception {
    
    public CustomerNotFoundException() {
        super("Customer not found.\n");
    }
    
    public CustomerNotFoundException(String message) {
        super(message);
    }
}
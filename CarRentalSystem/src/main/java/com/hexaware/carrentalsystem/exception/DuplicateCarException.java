package com.hexaware.carrentalsystem.exception;

public class DuplicateCarException extends Exception {
    
    public DuplicateCarException() {
        super("Duplicate car/vehicle entry.\n");
    }
    
    public DuplicateCarException(String message) {
        super(message);
    }
}
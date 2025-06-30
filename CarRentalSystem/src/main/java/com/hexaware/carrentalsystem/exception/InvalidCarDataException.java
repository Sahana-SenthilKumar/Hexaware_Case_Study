package com.hexaware.carrentalsystem.exception;

public class InvalidCarDataException extends Exception {
    
    public InvalidCarDataException() {
        super("Invalid car/vehicle data.\n");
    }
    
    public InvalidCarDataException(String message) {
        super(message);
    }

}

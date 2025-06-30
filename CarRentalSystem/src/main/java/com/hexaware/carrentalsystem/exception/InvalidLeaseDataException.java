package com.hexaware.carrentalsystem.exception;

public class InvalidLeaseDataException extends Exception {
    
    public InvalidLeaseDataException() {
        super("Invalid lease data.\n");
    }
    
    public InvalidLeaseDataException(String message) {
        super(message);
    }
}

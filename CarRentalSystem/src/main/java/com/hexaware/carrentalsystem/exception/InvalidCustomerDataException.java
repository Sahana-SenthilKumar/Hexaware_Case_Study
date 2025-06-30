package com.hexaware.carrentalsystem.exception;

public class InvalidCustomerDataException extends Exception {
    
    public InvalidCustomerDataException() {
        super("Invalid customer data.\n");
    }
    
    public InvalidCustomerDataException(String message) {
        super(message);
    }
}

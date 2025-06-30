package com.hexaware.carrentalsystem.exception;

public class InvalidPaymentDataException extends Exception {
    
    public InvalidPaymentDataException() {
        super("Invalid payment data.\n");
    }
    
    public InvalidPaymentDataException(String message) {
        super(message);
    }
}
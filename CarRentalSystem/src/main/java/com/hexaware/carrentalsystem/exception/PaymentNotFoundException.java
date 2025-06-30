package com.hexaware.carrentalsystem.exception;

public class PaymentNotFoundException extends Exception {
    
    public PaymentNotFoundException() {
        super("Payment not found.\n");
    }
    
    public PaymentNotFoundException(String message) {
        super(message);
    }
}
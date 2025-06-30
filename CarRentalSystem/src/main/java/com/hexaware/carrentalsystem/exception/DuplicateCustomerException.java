package com.hexaware.carrentalsystem.exception;

public class DuplicateCustomerException extends Exception {
    
    public DuplicateCustomerException() {
        super("Duplicate customer entry.\n");
    }
    
    public DuplicateCustomerException(String message) {
        super(message);
    }
}

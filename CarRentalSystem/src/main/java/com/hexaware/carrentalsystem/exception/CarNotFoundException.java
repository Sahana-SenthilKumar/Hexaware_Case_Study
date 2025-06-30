package com.hexaware.carrentalsystem.exception;

public class CarNotFoundException extends Exception{
	
	public CarNotFoundException() {
		super("Car/Vehicle not found.\n");
	}
	
    public CarNotFoundException(String message) {
        super(message);
    }

}

package com.hexaware.carrentalsystem.entity;

public class Customer {
	
	// Field Variables
	
    private int customerId;
	private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    
    
    
	// Default Constructor
    
    public Customer() {
    	
    }
    
    
    
	// Constructor
    
	public Customer(int customerId, String firstName, String lastName, String email, String phoneNumber) {
		super();
		this.customerId = customerId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phoneNumber = phoneNumber;
	}



	// Getter and Setter
	
	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}



	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}


	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
    
    
    
	// to.String();
    
    @Override
	public String toString() {
		return "Customer [customerID=" + customerId + ", firstName=" + firstName + ", lastName=" + lastName + ", email="
				+ email + ", phoneNumber=" + phoneNumber + "]";
	}
    

}

package com.hexaware.carrentalsystem.entity;

import java.sql.Date;

public class Payment {
	
	// Field Variables
	
    private int paymentId;
	private int leaseId;
    private Date paymentDate;
    private double amount;
    
    
    
	// Default Constructor
    
    public Payment() {
    	
    }
    
    
    
	// Constructor
    
	public Payment(int paymentId, int leaseId, Date paymentDate, double amount) {
		super();
		this.paymentId = paymentId;
		this.leaseId = leaseId;
		this.paymentDate = paymentDate;
		this.amount = amount;
	}

	public Payment(int leaseId, Date paymentDate, double amount) {
		super();
		this.leaseId = leaseId;
		this.paymentDate = paymentDate;
		this.amount = amount;
	}


	// Getter and Setter
	
	public int getPaymentId() {
		return paymentId;
	}
	public void setPaymentId(int paymentId) {
		this.paymentId = paymentId;
	}


	public int getLeaseId() {
		return leaseId;
	}
	public void setLeaseId(int leaseId) {
		this.leaseId = leaseId;
	}


	public Date getPaymentDate() {
		return paymentDate;
	}
	public void setPaymentDate(Date paymentDate) {
		this.paymentDate = paymentDate;
	}


	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}



	// to.String();
    
    @Override
	public String toString() {
		return "Payment [paymentID=" + paymentId + ", leaseID=" + leaseId + ", paymentDate=" + paymentDate + ", amount="
				+ amount + "]";
	}
    

}

package com.hexaware.carrentalsystem.entity;

import java.sql.Date;

public class Lease {
	
	// Field Variables
	
    private int leaseId;
	private int vehicleId;
    private int customerId;
    private Date startDate;
    private Date endDate;
    private String type;
    
    
    
	// Default Constructor
    
    public Lease() {
    	
    }
    
    
    
	// Constructor
    
	public Lease(int leaseId, int vehicleId, int customerId, Date startDate, Date endDate, String type) {
		super();
		this.leaseId = leaseId;
		this.vehicleId = vehicleId;
		this.customerId = customerId;
		this.startDate = startDate;
		this.endDate = endDate;
		this.type = type;
	}
	
	public Lease(int vehicleId, int customerId, Date startDate, Date endDate, String type) {
		super();
		this.vehicleId = vehicleId;
		this.customerId = customerId;
		this.startDate = startDate;
		this.endDate = endDate;
		this.type = type;
	}
	
	public Lease(int customerId,int vehicleId,  Date startDate, Date endDate) {
		super();
		this.customerId = customerId;
		this.vehicleId = vehicleId;
		this.startDate = startDate;
		this.endDate = endDate;
	}

	

	// Getter and Setter
	
	public int getLeaseId() {
		return leaseId;
	}
	public void setLeaseId(int leaseId) {
		this.leaseId = leaseId;
	}


	public int getVehicleId() {
		return vehicleId;
	}
	public void setVehicleId(int vehicleId) {
		this.vehicleId = vehicleId;
	}


	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}


	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}


	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}


	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}



	// to.String();
    
    @Override
	public String toString() {
		return "Lease [leaseID=" + leaseId + ", vehicleID=" + vehicleId + ", customerID=" + customerId + ", startDate="
				+ startDate + ", endDate=" + endDate + ", type=" + type + "]";
	}
    

}

package com.hexaware.carrentalsystem.service;

import java.sql.Date;
import java.util.List;

import com.hexaware.carrentalsystem.entity.*;
import com.hexaware.carrentalsystem.exception.*;

public interface ICarLeaseService {

    // -- Car Methods --
	
    public boolean addCar(Vehicle car) throws InvalidCarDataException, DuplicateCarException;
    
    public boolean removeCar(int carId) throws CarNotFoundException;
    
    public boolean updateCar(Vehicle car) throws CarNotFoundException, InvalidCarDataException;
    
    public Vehicle findCarById(int carId) throws CarNotFoundException;
    
    public List<Vehicle> listAvailableCars();
    
    public List<Vehicle> listRentedCars();
    
    public List<Vehicle> listAllCars();
    
    public boolean updateCarAvailability(int carId, String status) throws CarNotFoundException;
    
    

    // -- Customer Methods --
    
    public boolean addCustomer(Customer customer) throws InvalidCustomerDataException, DuplicateCustomerException;
    
    public boolean removeCustomer(int customerId) throws CustomerNotFoundException;
    
    public boolean updateCustomer(Customer customer) throws CustomerNotFoundException, InvalidCustomerDataException;
    
    public Customer findCustomerById(int customerId) throws CustomerNotFoundException;
    
    public List<Customer> listCustomers();
    
    

    // -- Lease Methods --
    
    public Lease createLease(int customerId, int carId, Date startDate, Date endDate)
        throws CustomerNotFoundException, CarNotFoundException, InvalidLeaseDataException, LeaseNotFoundException;
    
    public Lease returnCar(int leaseId) throws LeaseNotFoundException, CarNotFoundException;
    
    public List<Lease> listActiveLeases();
    
    public List<Lease> listLeaseHistory();
    
    public Lease findLeaseById(int leaseId) throws LeaseNotFoundException;

    
    
    // -- Payment Methods --
    
    public boolean recordPayment(int leaseId, double amount) throws LeaseNotFoundException, InvalidPaymentDataException;
    
    public List<Payment> getPaymentHistoryByCustomerId(int customerId) throws CustomerNotFoundException;
    
    public List<Payment> getAllPaymentHistory();
    
    public double calculateTotalRevenue();
    
    

    // -- Business Logic --
    
    public double calculateTotalCost(Lease lease) throws CarNotFoundException;
    

    
    // -- Validation Methods --
    
    public boolean validateCarData(Vehicle car) throws InvalidCarDataException;
    
    public boolean validateCustomerData(Customer customer) throws InvalidCustomerDataException;
    
    public boolean validateLeaseData(Lease lease) throws InvalidLeaseDataException;
    
    public boolean validatePaymentData(Payment payment) throws InvalidPaymentDataException;
    
    
    
    
}
package com.hexaware.carrentalsystem.dao;

import java.sql.Date;
import java.util.List;

import com.hexaware.carrentalsystem.entity.*;

import com.hexaware.carrentalsystem.exception.*;

public interface ICarLeaseRepository {

    // VehicleDAO : Car Management
	
    public boolean addCar(Vehicle car) throws InvalidCarDataException, DuplicateCarException;
    
    public boolean removeCar(int carId) throws CarNotFoundException;
    
    public boolean updateCar(Vehicle car) throws CarNotFoundException, InvalidCarDataException;
    
    public boolean updateCarAvailability(int carId, String status) throws CarNotFoundException;
    
    public List<Vehicle> listAvailableCars();
    
    public List<Vehicle> listRentedCars();
    
    public List<Vehicle> listAllCars();
    
    public Vehicle findCarById(int carId) throws CarNotFoundException;

    
    
    // CustomerDAO : Customer Management
    
    public boolean addCustomer(Customer customer) throws InvalidCustomerDataException, DuplicateCustomerException;
    
    public boolean removeCustomer(int customerId) throws CustomerNotFoundException;
    
    public boolean updateCustomer(Customer customer) throws CustomerNotFoundException, InvalidCustomerDataException;
    
    public List<Customer> listCustomers();
    
    public Customer findCustomerById(int customerId) throws CustomerNotFoundException;

    
    
    // LeaseDAO : Lease Management
    
    public Lease createLease(int customerId, int carId, Date startDate, Date endDate)
        throws CustomerNotFoundException, CarNotFoundException, InvalidLeaseDataException, LeaseNotFoundException;
    
    public Lease returnCar(int leaseId) throws LeaseNotFoundException, CarNotFoundException;
    
    public Lease findLeaseById(int leaseId) throws LeaseNotFoundException;
    
    public List<Lease> listActiveLeases();
    
    public List<Lease> listLeaseHistory();
    
    
    
    // PaymentDAO : Payment Handling
    
    public boolean recordPayment(Lease lease, double amount) throws InvalidPaymentDataException;
    
    public List<Payment> getPaymentHistoryByCustomerId(int customerId) throws CustomerNotFoundException;
    
    public List<Payment> getAllPaymentHistory();
    
    public double calculateTotalRevenue();
    
   

    // Helper Check Methods 

	 public boolean customerExists(int customerId);
	
	 public boolean carExists(int carId);

    
}

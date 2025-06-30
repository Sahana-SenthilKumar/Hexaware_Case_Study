package com.hexaware.carrentalsystem.service;

import java.sql.Date;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

import com.hexaware.carrentalsystem.dao.ICarLeaseRepository;
import com.hexaware.carrentalsystem.dao.CarLeaseRepositoryImpl;
import com.hexaware.carrentalsystem.entity.*;
import com.hexaware.carrentalsystem.exception.*;

public class CarLeaseServiceImpl implements ICarLeaseService {

    private ICarLeaseRepository dao = new CarLeaseRepositoryImpl();

    // -- Car Methods --
    
    // Add Car
    @Override
    public boolean addCar(Vehicle car) throws InvalidCarDataException, DuplicateCarException {
        
    	validateCarData(car);
    		
        if (dao.carExists(car.getVehicleId())) {
            throw new DuplicateCarException("Car already exists with id: " + car.getVehicleId()+"\n");
         }
            
         return dao.addCar(car);

    }
    

    // Remove Car
    @Override
    public boolean removeCar(int carId) throws CarNotFoundException {
    	
        if (!dao.carExists(carId)) {
        	throw new CarNotFoundException("Car not found with id: " + carId+"\n");
        }
        
        return dao.removeCar(carId);
    }

    
    // Update Car
    @Override
    public boolean updateCar(Vehicle car) throws CarNotFoundException, InvalidCarDataException {
    	
        if (!dao.carExists(car.getVehicleId())) {
        	throw new CarNotFoundException("Car not found with id: " + car.getVehicleId()+"\n");
        }
        
    	validateCarData(car);
        
        return dao.updateCar(car);
    }

    
    // Find Car by ID
    @Override
    public Vehicle findCarById(int carId) throws CarNotFoundException {
    	
        Vehicle car = dao.findCarById(carId);
        
        if (car == null) {
        	throw new CarNotFoundException("Car not found with id: " + carId+"\n");
        }
        
        return car;
    }

    
    // List Available Cars
    @Override
    public List<Vehicle> listAvailableCars() {
        
    	return dao.listAvailableCars();
    }

    
    // List Rented Cars
    @Override
    public List<Vehicle> listRentedCars() {
        
    	return dao.listRentedCars();
    }

    
    // List All Cars
    @Override
    public List<Vehicle> listAllCars() {
        
    	return dao.listAllCars();
    }

    
    // Update Car Availability
    @Override
    public boolean updateCarAvailability(int carId, String status) throws CarNotFoundException {
        
    	if (!dao.carExists(carId)) {
    		throw new CarNotFoundException("Car not found with id: " + carId +"\n");
    	}
    	
        return dao.updateCarAvailability(carId, status);
    }

    
    
    // -- Customer Methods --
    
    // Add Customer
    @Override
    public boolean addCustomer(Customer customer) throws InvalidCustomerDataException, DuplicateCustomerException {
        
    	validateCustomerData(customer);
    	
        if (dao.customerExists(customer.getCustomerId())) {
        	throw new DuplicateCustomerException("Customer already exists with id: " + customer.getCustomerId() +"\n");
        }
        
        return dao.addCustomer(customer);
    }

    
    // Remove Customer
    @Override
    public boolean removeCustomer(int customerId) throws CustomerNotFoundException {
        
    	if (!dao.customerExists(customerId)) {
    		throw new CustomerNotFoundException("Customer not found with id: " + customerId +"\n");
    	}
    	
        return dao.removeCustomer(customerId);
    }

    
    // Update Customer
    @Override
    public boolean updateCustomer(Customer customer) throws CustomerNotFoundException, InvalidCustomerDataException {

        if (!dao.customerExists(customer.getCustomerId())) {
        	throw new CustomerNotFoundException("Customer not found with id: " + customer.getCustomerId() +"\n");
        }
        
        validateCustomerData(customer);
        
        return dao.updateCustomer(customer);
    }

    
    // Find Customer by ID
    @Override
    public Customer findCustomerById(int customerId) throws CustomerNotFoundException {
    	
        Customer customer = dao.findCustomerById(customerId);
        
        if (customer == null) {
        	throw new CustomerNotFoundException("Customer not found with id: " + customerId +"\n");
        }
        
        return customer;
    }

    
    // List Customer
    @Override
    public List<Customer> listCustomers() {
    	
        return dao.listCustomers();
    }

    
    
    // -- Lease Methods --
    
    // Create Lease
    @Override
    public Lease createLease(int customerId, int carId, Date startDate, Date endDate)
            throws CustomerNotFoundException, CarNotFoundException, InvalidLeaseDataException, LeaseNotFoundException {
    	
        if (!dao.customerExists(customerId)) {
        	throw new CustomerNotFoundException("Customer not found with id: " + customerId +"\n");
        }
        
        if (!dao.carExists(carId)) {
        	throw new CarNotFoundException("Car not found with id: " + carId +"\n");
        }
        
        // Determine type first
        LocalDate start = startDate.toLocalDate();
        LocalDate end = endDate.toLocalDate();
        long days = ChronoUnit.DAYS.between(start, end);
        String type = days >= 28 ? "Monthly" : "Daily";

        // Create lease object with type set
        Lease lease = new Lease(carId, customerId, startDate, endDate, type);
        
        validateLeaseData(lease);
        
        return dao.createLease(customerId, carId, startDate, endDate);
    }

    
    // Return Car
    @Override
    public Lease returnCar(int leaseId) throws LeaseNotFoundException, CarNotFoundException {
        
    	Lease lease = dao.findLeaseById(leaseId);
        
    	if (lease == null) {
    		throw new LeaseNotFoundException("Lease not found with id: " + leaseId +"\n");
    	}
    	
        return dao.returnCar(leaseId);
    }

    
    // List Active Leases
    @Override
    public List<Lease> listActiveLeases() {
        
    	return dao.listActiveLeases();
    }

    
    // List Lease History
    @Override
    public List<Lease> listLeaseHistory() {
        
    	return dao.listLeaseHistory();
    }

    
    // Find Lease by ID
    @Override
    public Lease findLeaseById(int leaseId) throws LeaseNotFoundException {
    	
        Lease lease = dao.findLeaseById(leaseId);
        
        if (lease == null) {
        	throw new LeaseNotFoundException("Lease not found with id: " + leaseId +"\n");
        }
        
        return lease;
    }

    
    
    // -- Payment Methods --
    
    // Record Payment
    @Override
    public boolean recordPayment(int leaseId, double amount) throws LeaseNotFoundException, InvalidPaymentDataException {
        
    	Lease lease = dao.findLeaseById(leaseId);
        
    	if (lease == null) {
    		throw new LeaseNotFoundException("Lease not found with id: " + leaseId +"\n");
    	}
    	
    	Payment payment = new Payment(leaseId, new Date(System.currentTimeMillis()), amount);
    	
    	validatePaymentData(payment);
    	
        return dao.recordPayment(lease, amount);
        
    }

    
    // Get Payment History by Customer ID
    @Override
    public List<Payment> getPaymentHistoryByCustomerId(int customerId) throws CustomerNotFoundException {
        
    	if (!dao.customerExists(customerId)) {
    		throw new CustomerNotFoundException("Customer not found with id: " + customerId +"\n");
    	}
    	
        return dao.getPaymentHistoryByCustomerId(customerId);
    }

    
    // Get All Payment History
    @Override
    public List<Payment> getAllPaymentHistory() {
        
    	return dao.getAllPaymentHistory();
    }

    
    // Calculate Total Revenue 
    @Override
    public double calculateTotalRevenue() {
        
    	return dao.calculateTotalRevenue();
    }

    
    
    // -- Business Logic --
    @Override
    public double calculateTotalCost(Lease lease) throws CarNotFoundException {
        
        if (!dao.carExists(lease.getVehicleId())) {
        	throw new CarNotFoundException("Car not found with id: " + lease.getVehicleId()+"\n");
        }
        
        Vehicle car = dao.findCarById(lease.getVehicleId());

        LocalDate start = lease.getStartDate().toLocalDate();
        LocalDate end = lease.getEndDate().toLocalDate();
        
        long days = ChronoUnit.DAYS.between(start, end);

        double total;
        
        if ("Monthly".equalsIgnoreCase(lease.getType())) {
            long months = (days / 30);
            long remainingDays = (days % 30);

            total = car.getDailyRate() * 30 * months + car.getDailyRate() * remainingDays;
        } 
        else {
            total = car.getDailyRate() * days;
        }

        return total;
    }
    
    
    
    // -- Validation Methods --
    
    // Car Validate
    @Override
    public boolean validateCarData(Vehicle car) throws InvalidCarDataException {
        if (car == null) {
            throw new InvalidCarDataException("Car object cannot be null.\n");
        }
        if (car.getMake() == null || car.getMake().trim().isEmpty()) {
            throw new InvalidCarDataException("Make cannot be null or empty.\n");
        }
        if (car.getModel() == null || car.getModel().trim().isEmpty()) {
            throw new InvalidCarDataException("Model cannot be null or empty.\n");
        }
        if (car.getYear() <= 0) {
            throw new InvalidCarDataException("Invalid year.\n");
        }
        if (car.getDailyRate() <= 0) {
            throw new InvalidCarDataException("Invalid daily rate.\n");
        }
        if (car.getStatus() == null || car.getStatus().trim().isEmpty()) {
            throw new InvalidCarDataException("Status cannot be null or empty.\n");
        }
        if (!car.getStatus().equalsIgnoreCase("available") && !car.getStatus().equalsIgnoreCase("notAvailable")) {
            throw new InvalidCarDataException("Status can only be either 'available' (or) 'notAvailable'.\n");
        }
        if (car.getPassengerCapacity() <= 0) {
            throw new InvalidCarDataException("Invalid passenger capacity.\n");
        }
        if (car.getEngineCapacity() < 0) {
            throw new InvalidCarDataException("Invalid engine capacity.\n");
        }
        
        return true;
    }

    
    // Customer Validate
    @Override
    public boolean validateCustomerData(Customer customer) throws InvalidCustomerDataException {
        if (customer == null) {
            throw new InvalidCustomerDataException("Customer object cannot be null.\n");
        }
        if (customer.getFirstName() == null || customer.getFirstName().trim().isEmpty()) {
            throw new InvalidCustomerDataException("First name cannot be null or empty.\n");
        }
        if (!customer.getFirstName().matches("[a-zA-Z]+")) {
            throw new InvalidCustomerDataException("First name should contain only alphabets (A-Z, a-z).\n");
        }
        if (customer.getLastName() == null || customer.getLastName().trim().isEmpty()) {
            throw new InvalidCustomerDataException("Last name cannot be null or empty.\n");
        }
        if (!customer.getLastName().matches("[a-zA-Z]+")) {
            throw new InvalidCustomerDataException("Last name should contain only alphabets (A-Z, a-z).\n");
        }
        if (customer.getEmail() == null || customer.getEmail().trim().isEmpty()) {
            throw new InvalidCustomerDataException("Email cannot be null or empty.\n");
        }
        if ( !customer.getEmail().contains("@") || !customer.getEmail().contains(".")) {
        	throw new InvalidCustomerDataException("Email should contain both '.' and '@'. \n");
        }
        if (customer.getPhoneNumber() == null || customer.getPhoneNumber().trim().isEmpty()) {
            throw new InvalidCustomerDataException("Phone number cannot be null or empty.\n");
        }
        if (customer.getPhoneNumber().length() != 12) {
            throw new InvalidCustomerDataException("Phone number invalid format.\n");
        }
        if (!customer.getPhoneNumber().matches("[0-9\\-]+")) {
            throw new InvalidCustomerDataException("Phone number can only contain digits and hyphens (-).");
        }
        
        return true;
    }
    
    
    // Lease Validate
    @Override
    public boolean validateLeaseData(Lease lease) throws InvalidLeaseDataException {
        
    	if (lease.getStartDate() == null || lease.getEndDate() == null) {
            throw new InvalidLeaseDataException("Start date and end date cannot be null.\n");
        }
        if (lease.getEndDate().before(lease.getStartDate())) {
            throw new InvalidLeaseDataException("End date cannot be before start date.\n");
        }
        if (!lease.getType().equalsIgnoreCase("Monthly") && !lease.getType().equalsIgnoreCase("Daily")) {
            throw new InvalidLeaseDataException("Type can only be either 'Monthly' (or) 'Daily'.\n");
        }
        
        return true;
    }

    
    // Payment Validate
    @Override
    public boolean validatePaymentData(Payment payment) throws InvalidPaymentDataException {
        
    	if (payment.getAmount() <= 0) {
            throw new InvalidPaymentDataException("Payment amount must be greater than zero.\n");
        }
    	if (payment.getPaymentDate() == null) {
    		throw new InvalidPaymentDataException("Payment date cannot be null.\n");
    	}
    	
        return true;
    }

    
}

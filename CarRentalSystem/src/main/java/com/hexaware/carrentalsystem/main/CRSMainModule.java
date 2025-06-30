package com.hexaware.carrentalsystem.main;

import java.sql.Date;
import java.util.List;
import java.util.Scanner;

import com.hexaware.carrentalsystem.entity.*;
import com.hexaware.carrentalsystem.exception.*;
import com.hexaware.carrentalsystem.service.CarLeaseServiceImpl;
import com.hexaware.carrentalsystem.service.ICarLeaseService;
import com.hexaware.carrentalsystem.util.DBConnection;

public class CRSMainModule {

    private static final Scanner sc = new Scanner(System.in);
    private static final ICarLeaseService service = new CarLeaseServiceImpl();

    public static void main(String[] args) {

        while (true) {
            System.out.println("\n--- Car Rental System ---");
            System.out.println("1. Car Management");
            System.out.println("2. Customer Management");
            System.out.println("3. Lease Management");
            System.out.println("4. Payment Management");
            System.out.println("5. Exit");

            System.out.print("\nEnter your choice: ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    vehiclePortal();
                    break;
                case 2:
                    customerPortal();
                    break;
                case 3:
                    leasePortal();
                    break;
                case 4:
                    paymentPortal();
                    break;
                case 5:
                	DBConnection.closeConnection();
                    System.out.println("\nThank you for using Car Rental System. Existing System!");
                    System.exit(1);
                default:
                    System.out.println("\nInvalid option, try again.");
            }
        }
    }

    private static void vehiclePortal() {
        boolean inside = true;
        while (inside) {
            System.out.println("\n--- Vehicle Portal ---");
            System.out.println("1. Add Car");
            System.out.println("2. Update Car");
            System.out.println("3. Delete Car");
            System.out.println("4. View Car by ID");
            System.out.println("5. List All Cars");
            System.out.println("6. List Available Cars");
            System.out.println("7. List Rented Cars");
            System.out.println("8. Go Back");
            System.out.print("Enter your option: ");
            int opt = sc.nextInt(); sc.nextLine();

            switch (opt) {
                case 1:
                    try {
                        System.out.println("\nEnter car details:");
                        
                        System.out.println("Make (e.g., Toyota, Ford): ");
                        String make = sc.nextLine();
                        System.out.println("Model (e.g., Camry, Mustang): ");
                        String model = sc.nextLine();
                        System.out.println("Year: ");
                        int year = 0;
                        try {
                        	year = sc.nextInt(); sc.nextLine();
                        }
                        catch(Exception e) {
                        	System.err.println("Invalid year format.");
                        }
                        System.out.println("Daily Rate ($): ");
                        double rate = sc.nextDouble(); sc.nextLine();
                        System.out.println("Status (available/notAvailable): ");
                        String status = sc.nextLine();
                        System.out.println("Passenger Capacity: ");
                        int pc = sc.nextInt(); sc.nextLine();
                        System.out.println("Engine Capacity: ");
                        double ec = sc.nextDouble(); sc.nextLine();

                        Vehicle car = new Vehicle(0, make, model, year, rate, status, pc, ec);
                        boolean result = service.addCar(car);
                        if (result)
                            System.out.println("\nCar added successfully.\n-----------------------");

                    } 
                    catch (InvalidCarDataException e) {
                        System.err.println("\nInvalid Car Data: " + e.getMessage() + "\n-----------------------");
                    } 
                    catch (DuplicateCarException e) {
                        System.err.println("\nDuplicate Car: " + e.getMessage() + "\n-----------------------");
                    } 
                    catch (Exception e) {
                        System.err.println("\nError: " + e.getMessage() + "\n-----------------------");
                    }
                    break;

                case 2:
                    try {
                        System.out.println("\nEnter car update details:");
                        
                        System.out.println("Car ID (e.g., 1,2): ");
                        int id = sc.nextInt(); 
                        sc.nextLine();
                        System.out.println("Make (e.g., Toyota, Ford): ");
                        String make = sc.nextLine();
                        System.out.println("Model (e.g., Camry, Mustang): ");
                        String model = sc.nextLine();
                        System.out.println("Year: ");
                        int year = 0;
                        try {
                        	year = sc.nextInt(); sc.nextLine();
                        }
                        catch(Exception e) {
                        	System.err.println("Invalid year format.");
                        }
                        System.out.println("Daily Rate ($): ");
                        double rate = sc.nextDouble(); sc.nextLine();
                        System.out.println("Status (available/notAvailable): ");
                        String status = sc.nextLine();
                        System.out.println("Passenger Capacity: ");
                        int pc = sc.nextInt(); sc.nextLine();
                        System.out.println("Engine Capacity: ");
                        double ec = sc.nextDouble(); sc.nextLine();

                        Vehicle car = new Vehicle(id, make, model, year, rate, status, pc, ec);
                        boolean result = service.updateCar(car);
                        if (result)
                            System.out.println("\nCar updated successfully.\n-----------------------");

                    } 
                    catch (CarNotFoundException e) {
                        System.err.println("\nCar Not Found: " + e.getMessage() + "\n-----------------------");
                    } 
                    catch (InvalidCarDataException e) {
                        System.err.println("\nInvalid Car Data: " + e.getMessage() + "\n-----------------------");
                    } 
                    catch (Exception e) {
                        System.err.println("\nError: " + e.getMessage() + "\n-----------------------");
                    }
                    break;

                case 3:
                    try {
                        System.out.print("\nEnter Car ID to delete (e.g., 1, 2): ");
                        int id = sc.nextInt(); sc.nextLine();
                        boolean result = service.removeCar(id);
                        if (result)
                            System.out.println("\nCar deleted successfully.\n-----------------------");

                    } 
                    catch (CarNotFoundException e) {
                        System.err.println("\nCar Not Found: " + e.getMessage() + "\n-----------------------");
                    } 
                    catch (Exception e) {
                        System.err.println("\nError: " + e.getMessage() + "\n-----------------------");
                    }
                    break;

                case 4:
                    try {
                        System.out.print("\nEnter Car ID to view (e.g., 1, 2): ");
                        int id = sc.nextInt(); sc.nextLine();
                        Vehicle v = service.findCarById(id);
                        System.out.println("\n--- Car Details ---\n");
                        System.out.println("Car ID            : " + v.getVehicleId());
                        System.out.println("Make              : " + v.getMake());
                        System.out.println("Model             : " + v.getModel());
                        System.out.println("Year              : " + v.getYear());
                        System.out.println("Daily Rate ($)    : " + v.getDailyRate());
                        System.out.println("Status            : " + v.getStatus());
                        System.out.println("Passenger Capacity: " + v.getPassengerCapacity());
                        System.out.println("Engine Capacity   : " + v.getEngineCapacity());
                        System.out.println("-----------------------------\n");

                    } 
                    catch (CarNotFoundException e) {
                        System.err.println("Car Not Found: " + e.getMessage() + "\n-----------------------");
                    } 
                    catch (Exception e) {
                        System.err.println("Error: " + e.getMessage() + "\n-----------------------");
                    }
                    break;

                case 5:
                	List<Vehicle> allCars = service.listAllCars();

                	if (allCars == null || allCars.isEmpty()) {
                	    System.out.println("\nNo cars found.\n-----------------------------");
                	} 
                	else {
                	    System.out.println("\n--- All Cars ---\n");
                	    for (Vehicle v : allCars) {
                	        System.out.println("Car ID            : " + v.getVehicleId());
                	        System.out.println("Make              : " + v.getMake());
                	        System.out.println("Model             : " + v.getModel());
                	        System.out.println("Year              : " + v.getYear());
                	        System.out.println("Daily Rate ($)    : " + v.getDailyRate());
                	        System.out.println("Status            : " + v.getStatus());
                	        System.out.println("Passenger Capacity: " + v.getPassengerCapacity());
                	        System.out.println("Engine Capacity   : " + v.getEngineCapacity());
                	        System.out.println("-----------------------------");
                	    }
                	    System.out.println();
                	}
                    break;

                case 6:
                	List<Vehicle> availableCars = service.listAvailableCars();
                	if (availableCars == null || availableCars.isEmpty()) {
                	    System.out.println("\nNo available cars found.\n-----------------------------");
                	} 
                	else {
                	    System.out.println("\n--- Available Cars ---\n");
                	    for (Vehicle v : availableCars) {
                	        System.out.println("Car ID            : " + v.getVehicleId());
                	        System.out.println("Make              : " + v.getMake());
                	        System.out.println("Model             : " + v.getModel());
                	        System.out.println("Year              : " + v.getYear());
                	        System.out.println("Daily Rate ($)    : " + v.getDailyRate());
                	        System.out.println("Status            : " + v.getStatus());
                	        System.out.println("Passenger Capacity: " + v.getPassengerCapacity());
                	        System.out.println("Engine Capacity   : " + v.getEngineCapacity());
                	        System.out.println("-----------------------------");
                	    }
                	    System.out.println();
                	}
                    break;

                case 7:
                	List<Vehicle> rentedCars = service.listRentedCars();
                	if (rentedCars == null || rentedCars.isEmpty()) {
                	    System.out.println("\nNo rented cars found.\n-----------------------------");
                	} 
                	else {
                	    System.out.println("\n--- Rented Cars ---\n");
                	    for (Vehicle v : rentedCars) {
                	        System.out.println("Car ID            : " + v.getVehicleId());
                	        System.out.println("Make              : " + v.getMake());
                	        System.out.println("Model             : " + v.getModel());
                	        System.out.println("Year              : " + v.getYear());
                	        System.out.println("Daily Rate ($)    : " + v.getDailyRate());
                	        System.out.println("Status            : " + v.getStatus());
                	        System.out.println("Passenger Capacity: " + v.getPassengerCapacity());
                	        System.out.println("Engine Capacity   : " + v.getEngineCapacity());
                	        System.out.println("-----------------------------");
                	    }
                	    System.out.println();
                	}
                    break;

                case 8:
                    inside = false;
                    break;

                default:
                    System.out.println("\nInvalid option. Try again.\n-----------------------");
            }
        }
    }
    
    
    
    private static void customerPortal() {
        boolean inside = true;

        while (inside) {
            System.out.println("\n--- Customer Portal ---");
            System.out.println("1. Add Customer");
            System.out.println("2. Remove Customer");
            System.out.println("3. Update Customer");
            System.out.println("4. Find Customer by ID");
            System.out.println("5. List All Customers");
            System.out.println("6. Go Back");

            System.out.print("Enter your option: ");
            int opt = sc.nextInt();
            sc.nextLine();

            switch (opt) {
                case 1:
                    try {
                        System.out.println("\nEnter customer details:");
                        
                        System.out.print("First Name: ");
                        String fn = sc.nextLine();
                        System.out.print("Last Name: ");
                        String ln = sc.nextLine();
                        System.out.print("Email: ");
                        String email = sc.nextLine();
                        System.out.print("Phone Number (e.g., xxx-xxx-xxxx): ");
                        String phone = sc.nextLine();

                        Customer newCust = new Customer(0, fn, ln, email, phone);
                        if (service.addCustomer(newCust)) {
                            System.out.println("\nCustomer added successfully!\n-----------------------------");
                        }
                    } 
                    catch (InvalidCustomerDataException | DuplicateCustomerException e) {
                        System.err.println("\nError: " + e.getMessage() + "\n-----------------------------");
                    }
                    break;

                case 2:
                    try {
                        System.out.print("\nEnter Customer ID to remove (e.g., 101, 102): ");
                        int rid = sc.nextInt();
                        sc.nextLine();
                        if (service.removeCustomer(rid)) {
                            System.out.println("\nCustomer removed successfully!\n-----------------------------");
                        }
                    } 
                    catch (CustomerNotFoundException e) {
                        System.err.println("\nError: " + e.getMessage() + "\n-----------------------------");
                    }
                    break;

                case 3:
                    try {
                        System.out.print("\nEnter Customer ID to update (e.g., 101, 102): ");
                        
                        int uid = sc.nextInt();
                        sc.nextLine();
                        System.out.print("New First Name: ");
                        String nfn = sc.nextLine();
                        System.out.print("New Last Name: ");
                        String nln = sc.nextLine();
                        System.out.print("New Email: ");
                        String nemail = sc.nextLine();
                        System.out.print("New Phone Number (e.g., xxx-xxx-xxxx): ");
                        String nphone = sc.nextLine();

                        Customer updatedCust = new Customer(uid, nfn, nln, nemail, nphone);
                        if (service.updateCustomer(updatedCust)) {
                            System.out.println("\nCustomer updated successfully!\n-----------------------------");
                        }
                    } 
                    catch (CustomerNotFoundException | InvalidCustomerDataException e) {
                        System.err.println("\nError: " + e.getMessage() + "\n-----------------------------");
                    }
                    break;

                case 4:
                    try {
                        System.out.print("\nEnter Customer ID (e.g., 101, 102): ");
                        int fid = sc.nextInt();
                        sc.nextLine();
                        Customer c = service.findCustomerById(fid);
                        System.out.println("\n--- Customer Info ---\n");
                        System.out.println("Customer ID : " + c.getCustomerId());
                        System.out.println("First Name  : " + c.getFirstName());
                        System.out.println("Last Name   : " + c.getLastName());
                        System.out.println("Email       : " + c.getEmail());
                        System.out.println("Phone       : " + c.getPhoneNumber());
                        System.out.println("-----------------------------");
                    } 
                    catch (CustomerNotFoundException e) {
                        System.err.println("\nError: " + e.getMessage() + "\n-----------------------------");
                    }
                    break;

                case 5:
                    List<Customer> customers = service.listCustomers();
                    if (customers == null || customers.isEmpty()) {
                        System.out.println("\nNo customers found.\n-----------------------------");
                    } 
                    else {
                        System.out.println("\n--- All Customers ---\n");
                        for (Customer cust : customers) {
                            System.out.println("Customer ID : " + cust.getCustomerId());
                            System.out.println("First Name  : " + cust.getFirstName());
                            System.out.println("Last Name   : " + cust.getLastName());
                            System.out.println("Email       : " + cust.getEmail());
                            System.out.println("Phone       : " + cust.getPhoneNumber());
                            System.out.println("-----------------------------");
                        }
                    }
                    break;

                case 6:
                    inside = false;
                    break;

                default:
                    System.out.println("\nInvalid option. Try again.\n-----------------------------");
            }
        }
    }


    
    private static void leasePortal() {
        boolean inside = true;

        while (inside) {
            System.out.println("\n--- Lease Portal ---\n");
            System.out.println("1. Create Lease");
            System.out.println("2. Return Car");
            System.out.println("3. Find Lease by ID");
            System.out.println("4. List Active Leases");
            System.out.println("5. List All Leases");
            System.out.println("6. Calculate Total Cost for a Lease");
            System.out.println("7. Go Back");

            System.out.print("Enter your option: ");
            int opt = sc.nextInt();
            sc.nextLine();

            switch (opt) {
                case 1:
                    try {
                    	System.out.println("\nEnter lease details:");
                    	
                        System.out.print("\nEnter Customer ID (e.g., 101, 102): ");
                        int cid = sc.nextInt();
                        sc.nextLine();
                        System.out.print("Enter Car ID (e.g., 1, 2): ");
                        int carid = sc.nextInt();
                        sc.nextLine();
                        System.out.print("Enter Start Date (yyyy-mm-dd): ");
                        Date sd = null;
                        try {
                        	sd = Date.valueOf(sc.nextLine());
                        }
                        catch(Exception e) {
                        	System.err.println("Invalid date format.");
                        }
                        System.out.print("Enter End Date (yyyy-mm-dd): ");
                        Date ed = null;
                        try {
                        	ed =Date.valueOf(sc.nextLine());
                        }
                        catch(Exception e) {
                        	System.err.println("Invalid date format.");
                        }
                        
                        Lease lease = service.createLease(cid, carid, sd, ed);
                        System.out.println("\nLease created with ID: " + lease.getLeaseId() + "\n-----------------------------");
                    } 
                    catch (CustomerNotFoundException | CarNotFoundException | InvalidLeaseDataException | LeaseNotFoundException e) {
                        System.err.println("\nError: " + e.getMessage() + "\n-----------------------------");
                    }
                    break;

                case 2:
                    try {
                        System.out.print("\nEnter Lease ID to return (e.g., 1001, 1002): ");
                        int lid = sc.nextInt();
                        sc.nextLine();
                        Lease returned = service.returnCar(lid);
                        System.out.println("\nCar returned! Lease ID: " + returned.getLeaseId() + "\n-----------------------------");
                        System.out.println("--- Lease Return Details ---");
                        System.out.println("Lease ID   : " + returned.getLeaseId());
                        System.out.println("Car ID     : " + returned.getVehicleId());
                        System.out.println("Customer ID: " + returned.getCustomerId());
                        System.out.println("Start Date : " + returned.getStartDate());
                        System.out.println("End Date   : " + returned.getEndDate());
                        System.out.println("Type       : " + returned.getType());
                        System.out.println("-----------------------------\n");
                    } 
                    catch (LeaseNotFoundException | CarNotFoundException e) {
                        System.err.println("\nError: " + e.getMessage() + "\n-----------------------------");
                    }
                    break;

                case 3:
                    try {
                        System.out.print("\nEnter Lease ID (e.g., 1001, 1002): ");
                        int fid = sc.nextInt();
                        sc.nextLine();
                        Lease found = service.findLeaseById(fid);
                        System.out.println("\n--- Lease Info ---");
                        System.out.println("Lease ID   : " + found.getLeaseId());
                        System.out.println("Car ID     : " + found.getVehicleId());
                        System.out.println("Customer ID: " + found.getCustomerId());
                        System.out.println("Start Date : " + found.getStartDate());
                        System.out.println("End Date   : " + found.getEndDate());
                        System.out.println("Type       : " + found.getType());
                        System.out.println("-----------------------------");
                    } 
                    catch (LeaseNotFoundException e) {
                        System.err.println("\nError: " + e.getMessage() + "\n-----------------------------");
                    }
                    break;

                case 4:
                    List<Lease> active = service.listActiveLeases();
                    if (active == null || active.isEmpty()) {
                        System.out.println("\nNo active leases.\n-----------------------------");
                    } 
                    else {
                        System.out.println("\n--- Active Leases ---");
                        for (Lease l : active) {
                            System.out.println("Lease ID   : " + l.getLeaseId());
                            System.out.println("Car ID     : " + l.getVehicleId());
                            System.out.println("Customer ID: " + l.getCustomerId());
                            System.out.println("Start Date : " + l.getStartDate());
                            System.out.println("End Date   : " + l.getEndDate());
                            System.out.println("Type       : " + l.getType());
                            System.out.println("-----------------------------");
                        }
                    }
                    break;

                case 5:
                    List<Lease> all = service.listLeaseHistory();
                    if (all == null || all.isEmpty()) {
                        System.out.println("\nNo leases found.\n-----------------------------");
                    } 
                    else {
                        System.out.println("\n--- All Leases ---");
                        for (Lease l : all) {
                            System.out.println("Lease ID   : " + l.getLeaseId());
                            System.out.println("Car ID     : " + l.getVehicleId());
                            System.out.println("Customer ID: " + l.getCustomerId());
                            System.out.println("Start Date : " + l.getStartDate());
                            System.out.println("End Date   : " + l.getEndDate());
                            System.out.println("Type       : " + l.getType());
                            System.out.println("-----------------------------");
                        }
                    }
                    break;

                case 6:
                    try {
                        System.out.print("\nEnter Lease ID to calculate cost (e.g., 1001, 1002): ");
                        int lid = sc.nextInt();
                        sc.nextLine();
                        Lease lease = service.findLeaseById(lid);
                        double totalCost = service.calculateTotalCost(lease);
                        System.out.println("\nTotal cost for Lease ID " + lease.getLeaseId() + ": $" + totalCost + "\n-----------------------------");
                    } 
                    catch (LeaseNotFoundException | CarNotFoundException e) {
                        System.err.println("\nError: " + e.getMessage() + "\n-----------------------------");
                    }
                    break;

                case 7:
                    inside = false;
                    break;

                default:
                    System.out.println("\nInvalid option. Try again.\n-----------------------------");
            }
        }
    }
    
    
    
    
    private static void paymentPortal() {
        boolean inside = true;

        while (inside) {
            System.out.println("\n--- Payment Portal ---");
            System.out.println("1. Record Payment");
            System.out.println("2. Get Payment History by Customer ID");
            System.out.println("3. Get All Payment History");
            System.out.println("4. Get Total Revenue");
            System.out.println("5. Go Back");

            System.out.print("Enter your option: ");
            int opt = sc.nextInt();
            sc.nextLine();

            switch (opt) {
                case 1:
                    try {
                    	System.out.println("\nEnter payment details:");
                    	
                        System.out.print("\nEnter Lease ID (e.g., 1001, 1002): ");
                        int lid = sc.nextInt();
                        sc.nextLine();
                        System.out.print("Amount ($): ");
                        double amt = sc.nextDouble();
                        sc.nextLine();

                        if (service.recordPayment(lid, amt)) {
                            System.out.println("\nPayment recorded successfully!\n-----------------------------");
                        }
                    } 
                    catch (LeaseNotFoundException | InvalidPaymentDataException e) {
                        System.err.println("\nError: " + e.getMessage() + "\n-----------------------------");
                    }
                    break;

                case 2:
                    try {
                        System.out.print("\nEnter Customer ID (e.g., 101, 102): ");
                        int cid = sc.nextInt();
                        sc.nextLine();
                        List<Payment> plist = service.getPaymentHistoryByCustomerId(cid);

                        if (plist == null || plist.isEmpty()) {
                            System.out.println("\nNo payments found for Customer ID: " + cid + "\n-----------------------------");
                        } 
                        else {
                            System.out.println("\n--- Payments for Customer ID: " + cid + " ---");
                            for (Payment p : plist) {
                                System.out.println("Payment ID : " + p.getPaymentId());
                                System.out.println("Lease ID   : " + p.getLeaseId());
                                System.out.println("Date       : " + p.getPaymentDate());
                                System.out.println("Amount     : $" + p.getAmount());
                                System.out.println("-----------------------------");
                            }
                        }
                    } 
                    catch (CustomerNotFoundException e) {
                        System.err.println("\nError: " + e.getMessage() + "\n-----------------------------");
                    }
                    break;

                case 3:
                    List<Payment> all = service.getAllPaymentHistory();
                    if (all == null || all.isEmpty()) {
                        System.out.println("\nNo payment records found.\n-----------------------------");
                    } 
                    else {
                        System.out.println("\n--- All Payments ---");
                        for (Payment p : all) {
                            System.out.println("Payment ID : " + p.getPaymentId());
                            System.out.println("Lease ID   : " + p.getLeaseId());
                            System.out.println("Date       : " + p.getPaymentDate());
                            System.out.println("Amount     : $" + p.getAmount());
                            System.out.println("-----------------------------");
                        }
                    }
                    break;

                case 4:
                    double totalRev = service.calculateTotalRevenue();
                    System.out.println("\nTotal Revenue: $" + totalRev + "\n-----------------------------");
                    break;

                case 5:
                    inside = false;
                    break;

                default:
                    System.out.println("\nInvalid option. Try again.\n-----------------------------");
            }
        }
    }



}

               

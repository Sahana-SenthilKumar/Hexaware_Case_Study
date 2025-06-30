package com.hexaware.carrentalsystem.dao;

import java.sql.*;
import java.sql.Date;
import java.util.*;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import com.hexaware.carrentalsystem.entity.*;
import com.hexaware.carrentalsystem.exception.*;

import com.hexaware.carrentalsystem.util.DBConnection;

public class CarLeaseRepositoryImpl implements ICarLeaseRepository {

    // -- VehicleDAO Methods --

	// Add Car
    @Override
    public boolean addCar(Vehicle car) throws InvalidCarDataException, DuplicateCarException {
    	
        String query = "insert into Vehicle (make, model, year, dailyRate, status, passengerCapacity, engineCapacity) values (?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement pstmt = null;

        try {
        	
            Connection conn = DBConnection.getConnection();
            
            pstmt = conn.prepareStatement(query);
            
            pstmt.setString(1, car.getMake());
            pstmt.setString(2, car.getModel());
            pstmt.setInt(3, car.getYear());
            pstmt.setDouble(4, car.getDailyRate());
            pstmt.setString(5, car.getStatus());
            pstmt.setInt(6, car.getPassengerCapacity());
            pstmt.setDouble(7, car.getEngineCapacity());

            int rowsAffected = pstmt.executeUpdate();
            
            return rowsAffected > 0;

        } 
        catch (SQLException e) {
            e.printStackTrace();
        } 
        finally {
            DBConnection.closePreparedStatement(pstmt);
        }
        
        return false;
    }
    
    
    // Remove Car
    @Override
    public boolean removeCar(int carId) throws CarNotFoundException {
    	
        String query = "delete from Vehicle where vehicleID = ?";
        PreparedStatement pstmt = null;

        try {
        	
            Connection conn = DBConnection.getConnection();
            
            pstmt = conn.prepareStatement(query);
            
            pstmt.setInt(1, carId);

            int rowsAffected = pstmt.executeUpdate();
            
            return rowsAffected > 0;

        } 
        catch (SQLException e) {
            e.printStackTrace();

        } 
        finally {
            DBConnection.closePreparedStatement(pstmt);
        }
        
        return false;
    }

    
    // Update Car
    @Override
    public boolean updateCar(Vehicle car) throws CarNotFoundException, InvalidCarDataException {
        
    	String query = "update Vehicle set make=?, model=?, year=?, dailyRate=?, status=?, passengerCapacity=?, engineCapacity=? where vehicleID=?";
        PreparedStatement pstmt = null;

        try {
        	
            Connection conn = DBConnection.getConnection();
            
            pstmt = conn.prepareStatement(query);
            
            pstmt.setString(1, car.getMake());
            pstmt.setString(2, car.getModel());
            pstmt.setInt(3, car.getYear());
            pstmt.setDouble(4, car.getDailyRate());
            pstmt.setString(5, car.getStatus());
            pstmt.setInt(6, car.getPassengerCapacity());
            pstmt.setDouble(7, car.getEngineCapacity());
            pstmt.setInt(8, car.getVehicleId());

            int rowsAffected = pstmt.executeUpdate();
            
            return rowsAffected > 0;

        } 
        catch (SQLException e) {
            e.printStackTrace();
        } 
        finally {
            DBConnection.closePreparedStatement(pstmt);
        }
        
        return false;
    }

    
    // Update Car Availability
    @Override
    public boolean updateCarAvailability(int carId, String status) throws CarNotFoundException {
        
    	String query = "update Vehicle set status=? where vehicleID=?";
        PreparedStatement pstmt = null;

        try {
        	
            Connection conn = DBConnection.getConnection();
            
            pstmt = conn.prepareStatement(query);
            
            pstmt.setString(1, status);
            pstmt.setInt(2, carId);

            int rowsAffected = pstmt.executeUpdate();
            
            return rowsAffected > 0;

        } 
        catch (SQLException e) {
            e.printStackTrace();  
        } 
        finally {
            DBConnection.closePreparedStatement(pstmt);
        }
        
        return false;
    }

    
    // List Available Cars
    @Override
    public List<Vehicle> listAvailableCars() {
    	
        String query = "select * from Vehicle where status = 'available'";
        Statement stmt = null;
        ResultSet rs = null;
        List<Vehicle> cars = new ArrayList<>();

        try {
        	
            Connection conn = DBConnection.getConnection();
            
            stmt = conn.createStatement();
            rs = stmt.executeQuery(query);

            while (rs.next()) {
                cars.add(new Vehicle(
                    rs.getInt("vehicleID"),
                    rs.getString("make"),
                    rs.getString("model"),
                    rs.getInt("year"),
                    rs.getDouble("dailyRate"),
                    rs.getString("status"),
                    rs.getInt("passengerCapacity"),
                    rs.getDouble("engineCapacity")
                ));
            }

        } 
        catch (SQLException e) {
            e.printStackTrace();
        } 
        finally {
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(stmt);
        }

        return cars;
    }

    
    // List Rented Cars
    @Override
    public List<Vehicle> listRentedCars() {
    	
        String query = "select * from Vehicle where status = 'notAvailable'";
        Statement stmt = null;
        ResultSet rs = null;
        List<Vehicle> cars = new ArrayList<>();

        try {
        	
            Connection conn = DBConnection.getConnection();
            
            stmt = conn.createStatement();
            rs = stmt.executeQuery(query);

            while (rs.next()) {
                cars.add(new Vehicle(
                    rs.getInt("vehicleID"),
                    rs.getString("make"),
                    rs.getString("model"),
                    rs.getInt("year"),
                    rs.getDouble("dailyRate"),
                    rs.getString("status"),
                    rs.getInt("passengerCapacity"),
                    rs.getDouble("engineCapacity")
                ));
            }

        } 
        catch (SQLException e) {
            e.printStackTrace();
        } 
        finally {
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(stmt);
        }

        return cars;
    }

    
    // List All Cars
    @Override
    public List<Vehicle> listAllCars() {
    	
        String query = "select * from Vehicle";
        Statement stmt = null;
        ResultSet rs = null;
        List<Vehicle> cars = new ArrayList<>();

        try {
        	
            Connection conn = DBConnection.getConnection();
            
            stmt = conn.createStatement();
            rs = stmt.executeQuery(query);

            while (rs.next()) {
                cars.add(new Vehicle(
                    rs.getInt("vehicleID"),
                    rs.getString("make"),
                    rs.getString("model"),
                    rs.getInt("year"),
                    rs.getDouble("dailyRate"),
                    rs.getString("status"),
                    rs.getInt("passengerCapacity"),
                    rs.getDouble("engineCapacity")
                ));
            }

        } 
        catch (SQLException e) {
            e.printStackTrace();
        } 
        finally {
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(stmt);
        }

        return cars;
    }
    
   
    // Find Car by ID
    @Override
    public Vehicle findCarById(int carId) throws CarNotFoundException {
    	
        String query = "select * from Vehicle where vehicleID = ?";
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Vehicle car = null;

        try {
        	
            Connection conn = DBConnection.getConnection();
            
            pstmt = conn.prepareStatement(query);
            
            pstmt.setInt(1, carId);
            
            rs = pstmt.executeQuery();

            if (rs.next()) {
                car = new Vehicle(
                    rs.getInt("vehicleID"),
                    rs.getString("make"),
                    rs.getString("model"),
                    rs.getInt("year"),
                    rs.getDouble("dailyRate"),
                    rs.getString("status"),
                    rs.getInt("passengerCapacity"),
                    rs.getDouble("engineCapacity")
                );
            } 

        } 
        catch (SQLException e) {
            e.printStackTrace();
        } 
        finally {
            DBConnection.closeResultSet(rs);
            DBConnection.closePreparedStatement(pstmt);
        }

        return car;
    }

    


    // -- CustomerDAO --

    // Add Customer
	 @Override
	 public boolean addCustomer(Customer customer) throws InvalidCustomerDataException, DuplicateCustomerException {
	     
		 String query = "insert into Customer (firstName, lastName, email, phoneNumber) values (?, ?, ?, ?)";
	     PreparedStatement pstmt = null;
	
	     try {
	    	 
	         Connection conn = DBConnection.getConnection();
	         
	         pstmt = conn.prepareStatement(query);
	         
	         pstmt.setString(1, customer.getFirstName());
	         pstmt.setString(2, customer.getLastName());
	         pstmt.setString(3, customer.getEmail());
	         pstmt.setString(4, customer.getPhoneNumber());
	
	         int rowsAffected = pstmt.executeUpdate();
	         
	         return rowsAffected > 0;
	
	     } 
	     catch (SQLException e) {
	         e.printStackTrace();
	     } 
	     finally {
	         DBConnection.closePreparedStatement(pstmt);
	     }
	     
	     return false;
	     
	 }
	
	 
	 // Remove Customer
	 @Override
	 public boolean removeCustomer(int customerId) throws CustomerNotFoundException {
	    
		 String query = "delete from Customer where customerID = ?";
	     PreparedStatement pstmt = null;
	
	     try {
	    	 
	         Connection conn = DBConnection.getConnection();
	         
	         pstmt = conn.prepareStatement(query);
	         
	         pstmt.setInt(1, customerId);
	
	         int rowsAffected = pstmt.executeUpdate();
	         
	         return rowsAffected > 0;
	         
	     } 
	     catch (SQLException e) {
	         e.printStackTrace();
	     } 
	     finally {
	         DBConnection.closePreparedStatement(pstmt);
	     }
	     
	     return false;
	 }
	
	 
	 // Update Customer
	 @Override
	 public boolean updateCustomer(Customer customer) throws CustomerNotFoundException, InvalidCustomerDataException {
	     
		 String query = "update Customer set firstName=?, lastName=?, email=?, phoneNumber=? where customerID=?";
	     PreparedStatement pstmt = null;
	
	     try {
	    	 
	         Connection conn = DBConnection.getConnection();
	         
	         pstmt = conn.prepareStatement(query);
	         
	         pstmt.setString(1, customer.getFirstName());
	         pstmt.setString(2, customer.getLastName());
	         pstmt.setString(3, customer.getEmail());
	         pstmt.setString(4, customer.getPhoneNumber());
	         pstmt.setInt(5, customer.getCustomerId());
	
	         int rowsAffected = pstmt.executeUpdate();
	         
	         return rowsAffected > 0;
	
	     } catch (SQLException e) {
	         e.printStackTrace();
	     } finally {
	         DBConnection.closePreparedStatement(pstmt);
	     }
	     
	     return false;
	 }
	
	 
	 // List Customers
	 @Override
	 public List<Customer> listCustomers() {
		 
	     String query = "select * from Customer";
	     Statement stmt = null;
	     ResultSet rs = null;
	     List<Customer> customers = new ArrayList<>();
	
	     try {
	    	 
	         Connection conn = DBConnection.getConnection();
	         
	         stmt = conn.createStatement();
	         rs = stmt.executeQuery(query);
	
	         while (rs.next()) {
	             customers.add(new Customer(
	                 rs.getInt("customerID"),
	                 rs.getString("firstName"),
	                 rs.getString("lastName"),
	                 rs.getString("email"),
	                 rs.getString("phoneNumber")
	             ));
	         }
	
	     } catch (SQLException e) {
	         e.printStackTrace();
	     } finally {
	         DBConnection.closeResultSet(rs);
	         DBConnection.closeStatement(stmt);
	     }
	
	     return customers;
	 }
	
	 
	 // Find Customer by ID
	 @Override
	 public Customer findCustomerById(int customerId) throws CustomerNotFoundException {
	     
		 String query = "select * from Customer where customerID = ?";
	     PreparedStatement pstmt = null;
	     ResultSet rs = null;
	     Customer customer = null;
	
	     try {
	    	 
	         Connection conn = DBConnection.getConnection();
	         
	         pstmt = conn.prepareStatement(query);
	         
	         pstmt.setInt(1, customerId);
	         
	         rs = pstmt.executeQuery();
	
	         if (rs.next()) {
	             customer = new Customer(
	                 rs.getInt("customerID"),
	                 rs.getString("firstName"),
	                 rs.getString("lastName"),
	                 rs.getString("email"),
	                 rs.getString("phoneNumber")
	             );
	         } 
	
	     } 
	     catch (SQLException e) {
	         e.printStackTrace();
	     } 
	     finally {
	         DBConnection.closeResultSet(rs);
	         DBConnection.closePreparedStatement(pstmt);
	     }
	
	     return customer;
	 }
	 
	 
	 

	// -- LeaseDAO --

	// Create Lease
	@Override
	public Lease createLease(int customerId, int carId, Date startDate, Date endDate)
	        throws CustomerNotFoundException, CarNotFoundException, InvalidLeaseDataException, LeaseNotFoundException {
	    
		String query = "insert into Lease (vehicleID, customerID, startDate, endDate, type) values (?, ?, ?, ?, ?)";
	    PreparedStatement pstmt = null;
	    Lease lease = null;
	    ResultSet rs = null;

	    try {
	    	
	        Connection conn = DBConnection.getConnection();

	        // Calculate lease type
	        LocalDate start = startDate.toLocalDate();
	        LocalDate end = endDate.toLocalDate();
	        long days = ChronoUnit.DAYS.between(start, end);
	        String type = days >= 28 ? "Monthly" : "Daily";


	        pstmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
	        pstmt.setInt(1, carId);
	        pstmt.setInt(2, customerId);
	        pstmt.setDate(3, startDate);
	        pstmt.setDate(4, endDate);
	        pstmt.setString(5, type);

	        pstmt.executeUpdate();

	        rs = pstmt.getGeneratedKeys();
	        
	        if (rs.next()) {
	            int leaseId = rs.getInt(1);
	            lease =  findLeaseById(leaseId);
	        }

	        // Update car status
	        updateCarAvailability(carId, "notAvailable");

	    } 
	    catch (SQLException e) {
	        e.printStackTrace();
	    } 
	    finally {
	    	DBConnection.closeResultSet(rs);
	        DBConnection.closePreparedStatement(pstmt);
	    }

	    return lease;
	}
	
	
	// Find Lease by ID
	@Override
	public Lease findLeaseById(int leaseId) throws LeaseNotFoundException{
		
	    String query = "select * from Lease where leaseID = ?";
	    PreparedStatement pstmt = null;
	    ResultSet rs = null;
	    Lease lease = null;

	    try {
	    	
	        Connection conn = DBConnection.getConnection();
	        
	        pstmt = conn.prepareStatement(query);
	        
	        pstmt.setInt(1, leaseId);
	        
	        rs = pstmt.executeQuery();

	        if (rs.next()) {
	            lease = new Lease(
	                rs.getInt("leaseID"),
	                rs.getInt("vehicleID"),
	                rs.getInt("customerID"),
	                rs.getDate("startDate"),
	                rs.getDate("endDate"),
	                rs.getString("type")
	            );
	        }

	    } 
	    catch (SQLException e) {
	        e.printStackTrace();
	    } 
	    finally {
	        DBConnection.closeResultSet(rs);
	        DBConnection.closePreparedStatement(pstmt);
	    }

	    return lease;
	}

	
	// Return Car
	@Override
	public Lease returnCar(int leaseId) throws LeaseNotFoundException, CarNotFoundException {
	    
		String query = "select * from Lease where leaseID = ?";
	    PreparedStatement pstmt = null;
	    ResultSet rs = null;
	    Lease lease = null;

	    try {
	    	
	        Connection conn = DBConnection.getConnection();
	        
	        pstmt = conn.prepareStatement(query);
	        
	        pstmt.setInt(1, leaseId);
	        
	        rs = pstmt.executeQuery();

	        if (rs.next()) {
	            lease = new Lease(
	                rs.getInt("leaseID"),
	                rs.getInt("vehicleID"),
	                rs.getInt("customerID"),
	                rs.getDate("startDate"),
	                rs.getDate("endDate"),
	                rs.getString("type")
	            );

	            // Update car status to available
	            updateCarAvailability(rs.getInt("vehicleID"), "available");
	        }

	    } 
	    catch (SQLException e) {
	        e.printStackTrace();
	    } 
	    finally {
	        DBConnection.closeResultSet(rs);
	        DBConnection.closePreparedStatement(pstmt);
	    }

	    return lease;
	}

	
	// List Active Lease
	@Override
	public List<Lease> listActiveLeases() {
		
	    String query = "select * from Lease where endDate >= curdate()";
	    Statement stmt = null;
	    ResultSet rs = null;
	    List<Lease> leases = new ArrayList<>();

	    try {
	    	
	        Connection conn = DBConnection.getConnection();
	        
	        stmt = conn.createStatement();
	        
	        rs = stmt.executeQuery(query);

	        while (rs.next()) {
	            leases.add(new Lease(
	                rs.getInt("leaseID"),
	                rs.getInt("vehicleID"),
	                rs.getInt("customerID"),
	                rs.getDate("startDate"),
	                rs.getDate("endDate"),
	                rs.getString("type")
	            ));
	        }

	    } 
	    catch (SQLException e) {
	        e.printStackTrace();
	    } 
	    finally {
	        DBConnection.closeResultSet(rs);
	        DBConnection.closeStatement(stmt);
	    }

	    return leases;
	}

	
	// List Lease History
	@Override
	public List<Lease> listLeaseHistory() {
		
	    String query = "select * from Lease";
	    Statement stmt = null;
	    ResultSet rs = null;
	    List<Lease> leases = new ArrayList<>();

	    try {
	        Connection conn = DBConnection.getConnection();
	        
	        stmt = conn.createStatement();
	        
	        rs = stmt.executeQuery(query);

	        while (rs.next()) {
	            leases.add(new Lease(
	                rs.getInt("leaseID"),
	                rs.getInt("vehicleID"),
	                rs.getInt("customerID"),
	                rs.getDate("startDate"),
	                rs.getDate("endDate"),
	                rs.getString("type")
	            ));
	        }

	    } 
	    catch (SQLException e) {
	        e.printStackTrace();
	    } 
	    finally {
	        DBConnection.closeResultSet(rs);
	        DBConnection.closeStatement(stmt);
	    }

	    return leases;
	}


	

	// -- PaymentDAO --

	// Record Payment
	@Override
	public boolean recordPayment(Lease lease, double amount) throws InvalidPaymentDataException {
	    
		String query = "insert into Payment (leaseID, paymentDate, amount) values (?, ?, ?)";
	    PreparedStatement pstmt = null;

	    try {
	    	
	        Connection conn = DBConnection.getConnection();
	        
	        pstmt = conn.prepareStatement(query);
	        
	        pstmt.setInt(1, lease.getLeaseId());
	        pstmt.setDate(2, new Date(System.currentTimeMillis()));
	        pstmt.setDouble(3, amount);

	        int rowsAffected = pstmt.executeUpdate();
	        return rowsAffected > 0;

	    } 
	    catch (SQLException e) {
	        e.printStackTrace();
	    } 
	    finally {
	        DBConnection.closePreparedStatement(pstmt);
	    }
	    
	    return false;
	    
	}

	
	// Get Payment History by Customer ID
	@Override
	public List<Payment> getPaymentHistoryByCustomerId(int customerId) throws CustomerNotFoundException {
	    
		String query = "select p.* from Payment p join Lease l on p.leaseID = l.leaseID where l.customerID = ?";
	    PreparedStatement pstmt = null;
	    ResultSet rs = null;
	    List<Payment> payments = new ArrayList<>();

	    try {
	    	
	        Connection conn = DBConnection.getConnection();
	        
	        pstmt = conn.prepareStatement(query);
	        
	        pstmt.setInt(1, customerId);
	        
	        rs = pstmt.executeQuery();

	        while (rs.next()) {
	            payments.add(new Payment(
	                rs.getInt("paymentID"),
	                rs.getInt("leaseID"),
	                rs.getDate("paymentDate"),
	                rs.getDouble("amount")
	            ));
	        }


	    } 
	    catch (SQLException e) {
	        e.printStackTrace();
	    } 
	    finally {
	        DBConnection.closeResultSet(rs);
	        DBConnection.closePreparedStatement(pstmt);
	    }

	    return payments;
	}

	// Get All Payment History
	@Override
	public List<Payment> getAllPaymentHistory() {
		
	    String query = "select * from Payment";
	    Statement stmt = null;
	    ResultSet rs = null;
	    List<Payment> payments = new ArrayList<>();

	    try {
	    	
	        Connection conn = DBConnection.getConnection();
	        
	        stmt = conn.createStatement();
	        
	        rs = stmt.executeQuery(query);

	        while (rs.next()) {
	            payments.add(new Payment(
	                rs.getInt("paymentID"),
	                rs.getInt("leaseID"),
	                rs.getDate("paymentDate"),
	                rs.getDouble("amount")
	            ));
	        }

	    } 
	    catch (SQLException e) {
	        e.printStackTrace();
	    } 
	    finally {
	        DBConnection.closeResultSet(rs);
	        DBConnection.closeStatement(stmt);
	    }

	    return payments;
	}

	
	// calculate Total Revenue
	@Override
	public double calculateTotalRevenue() {
		
	    String query = "select sum(amount) as totalRevenue from Payment";
	    Statement stmt = null;
	    ResultSet rs = null;
	    double revenue = 0;

	    try {
	    	
	        Connection conn = DBConnection.getConnection();
	        
	        stmt = conn.createStatement();
	        
	        rs = stmt.executeQuery(query);

	        if (rs.next()) {
	            revenue = rs.getDouble("totalRevenue");
	        }

	    } 
	    catch (SQLException e) {
	        e.printStackTrace();
	    } 
	    finally {
	        DBConnection.closeResultSet(rs);
	        DBConnection.closeStatement(stmt);
	    }

	    return revenue;
	}

	
	
	
	// -- Helper Methods --
	
	// Customer Exists
	@Override
	public boolean customerExists(int customerId) {
		
	    String query = "select * FROM Customer where customerID = ?";
	    PreparedStatement pstmt = null;
	    ResultSet rs = null;
	    boolean exists = false;

	    try {
	    	
	        Connection conn = DBConnection.getConnection();
	        
	        pstmt = conn.prepareStatement(query);
	        
	        pstmt.setInt(1, customerId);
	        
	        rs = pstmt.executeQuery();

	        exists = rs.next();

	    } 
	    catch (SQLException e) {
	        e.printStackTrace();
	    } 
	    finally {
	        DBConnection.closeResultSet(rs);
	        DBConnection.closePreparedStatement(pstmt);
	    }

	    return exists;
	}
	
	
	// Car Exists
	@Override
	public boolean carExists(int carId) {
		
	    String query = "select * from Vehicle where vehicleID = ?";
	    PreparedStatement pstmt = null;
	    ResultSet rs = null;
	    boolean exists = false;

	    try {
	    	
	        Connection conn = DBConnection.getConnection();
	        
	        pstmt = conn.prepareStatement(query);
	        
	        pstmt.setInt(1, carId);
	        
	        rs = pstmt.executeQuery();

	        exists = rs.next();

	    } 
	    catch (SQLException e) {
	        e.printStackTrace();
	    } 
	    finally {
	        DBConnection.closeResultSet(rs);
	        DBConnection.closePreparedStatement(pstmt);
	    }

	    return exists;
	}
  

}

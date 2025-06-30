package com.hexaware.carrentalsystem.util;

import java.sql.*;

public class DBConnection {
	
	private static Connection connection = null;
	
	// Open Connection
	public static Connection getConnection() {
		
		if(connection ==  null) {
			
			try {

				String connString = DBPropertyUtil.getPropertyString("src/main/resources/db.properties");
				connection = DriverManager.getConnection(connString);
				System.out.println("\nDatabase Connected "+connection+"\n");
				
				
			}
			catch(SQLException e ){
				System.out.println("\nConnection Failed.\n");
				e.printStackTrace();
				
			}
		}
		
		return connection;
		
	}
	
	
	// Close Connection
	public static void closeConnection() {
		
		try {
			
			if(connection != null && !connection.isClosed()) {
				connection.close();
				System.out.println("\nDatabase connection closed.\n");	
				connection = null;
			}
			else {
				System.out.println("\nNo database connection found.\n");	
			}

			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	
	// Close Statement
	public static void closeStatement(Statement stmt) {
		
		try {
			if (stmt != null && !stmt.isClosed()) {
				stmt.close();
			}
			
		}
		catch(SQLException e) {
			System.out.println("\nError while closing Statement.\n");
			e.printStackTrace();
		}
		
	}
	
	
	// Close ResultSet
	public static void closeResultSet(ResultSet rs) {
		
	    try {
	        if (rs != null && !rs.isClosed()) {
	            rs.close();
	        }
	        
	    } 
	    catch (SQLException e) {
	        System.out.println("\nError while closing ResultSet.\n");
	        e.printStackTrace();
	    }

		
	}
	
	
	// Close PreparedStatement
	public static void closePreparedStatement(PreparedStatement pstmt) {
		
	    try {
	        
	    	if (pstmt != null && !pstmt.isClosed()) {
	            pstmt.close();
	        }
	        
	    } 
	    catch (SQLException e) {
	        System.out.println("\nError while closing PreparedStatement.\n");
	        e.printStackTrace();
	    }

		
	}
	
	
}

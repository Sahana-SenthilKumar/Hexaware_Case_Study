package com.hexaware.carrentalsystem.util;

import java.io.IOException;
import java.util.Properties;
import java.io.FileInputStream;


public class DBPropertyUtil {
	
	public static String getPropertyString(String filename) {
		
		
		Properties property = new Properties();
		
		try { 
			
			property.load(new FileInputStream(filename));

            String hostname = property.getProperty("hostname");
            String port = property.getProperty("port");
            String dbname = property.getProperty("dbname");
            String username = property.getProperty("username");
            String password = property.getProperty("password");

            String url = "jdbc:mysql://" + hostname + ":" + port + "/" + dbname 
                       + "?user=" + username + "&password=" + password;

            return url;

        } 
		catch (IOException ex) {
			System.out.println("\n Unable to find "+filename+"\n");
            ex.printStackTrace();
        }
		
        return null;
	}

}

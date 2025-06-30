package com.hexaware.carrentalsystem.test;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Date;
import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.hexaware.carrentalsystem.entity.*;

import com.hexaware.carrentalsystem.exception.*;

import com.hexaware.carrentalsystem.service.*;

public class CarLeaseServiceTest {

    private ICarLeaseService service;

    @BeforeEach
    public void setUp() {
        service = new CarLeaseServiceImpl();
    }

    // Test car creation
    @Test
    public void testAddCar_Success() {
        Vehicle car = new Vehicle(0, "TestMake", "TestModel", 2023, 120.0, "available", 4, 2.0);
        assertDoesNotThrow(() -> service.addCar(car));
    }

    // Test lease creation
    @Test
    public void testCreateLease_Success() {
        int customerId = 101; // Must exist in DB
        int carId = 1;       // Must exist & be available

        Date start = Date.valueOf(LocalDate.now());
        Date end = Date.valueOf(LocalDate.now().plusDays(10));

        assertDoesNotThrow(() -> service.createLease(customerId, carId, start, end));
    }

    // Test lease retrieval
    @Test
    public void testFindLeaseById_Success() {
        int leaseId = 1001; // Must exist in DB
        assertDoesNotThrow(() -> service.findLeaseById(leaseId));
    }

    // Test CustomerNotFoundException
    @Test
    public void testCustomerNotFoundException() {
        int invalidCustomerId = 9999;
        Exception exception = assertThrows(CustomerNotFoundException.class, () -> {
            service.findCustomerById(invalidCustomerId);
        });
        assertTrue(exception.getMessage().contains("Customer not found"));
    }

    // Test CarNotFoundException
    @Test
    public void testCarNotFoundException() {
        int invalidCarId = 9999;
        Exception exception = assertThrows(CarNotFoundException.class, () -> {
            service.findCarById(invalidCarId);
        });
        assertTrue(exception.getMessage().contains("Car not found"));
    }

    // Test LeaseNotFoundException
    @Test
    public void testLeaseNotFoundException() {
        int invalidLeaseId = 9999;
        Exception exception = assertThrows(LeaseNotFoundException.class, () -> {
            service.findLeaseById(invalidLeaseId);
        });
        assertTrue(exception.getMessage().contains("Lease not found"));
    }
}


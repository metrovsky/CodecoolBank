package com.codecool.metrovsky.bank.model;

import org.junit.jupiter.api.Test;

import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;

import static org.junit.jupiter.api.Assertions.assertEquals;


class CustomerTest {

    @Test
    public void isGetLoginValid() throws NoSuchAlgorithmException {
        Customer customer = new Customer(null, null, "Test", "Test", null,
                false);
        assertEquals("Test", customer.getLogin());
    }

    @Test
    public void isGetCreateDateValid() throws NoSuchAlgorithmException {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        Customer customer = new Customer(null, null, null, "Test", timestamp,
                false);
        assertEquals(timestamp, customer.getCreateDate());
    }

    @Test
    public void isSetFirstNameValid() throws NoSuchAlgorithmException {
        Customer customer = new Customer(null, null, null, "Test", null,
                false);
        customer.setFirstName("Test");
        assertEquals("Test", customer.getFirstName());
    }

    @Test
    public void isSetLastNameValid() throws NoSuchAlgorithmException {
        Customer customer = new Customer(null, null, null, "Test", null,
                false);
        customer.setLastName("Test");
        assertEquals("Test", customer.getLastName());
    }

    @Test
    public void isSetLastLoginValid() throws NoSuchAlgorithmException {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        Customer customer = new Customer(1, null, null, null, "Test", null,
                false, timestamp);
        timestamp = new Timestamp(System.currentTimeMillis());
        customer.setLastLogin(timestamp);
        assertEquals(timestamp, customer.getLastLogin());
    }

}
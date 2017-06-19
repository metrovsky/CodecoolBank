package com.codecool.metrovsky.bank.controller;

import com.codecool.metrovsky.bank.dao.implementation.CustomerDaoImpl;
import com.codecool.metrovsky.bank.model.Customer;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


class CustomerControllerTest {

    private static CustomerController customerController;
    private static Connection conn;

    @AfterAll
    public static void emptyDatabase() throws SQLException {

        Statement dbStatement = CustomerControllerTest.conn.createStatement();
        String query = "DELETE FROM Customers";

        dbStatement.execute(query);
        dbStatement.close();
    }

    @BeforeEach
    public void createController() throws SQLException {

        CustomerControllerTest.conn = DriverManager.getConnection("jdbc:sqlite:src/main/resources/database/metrovskybank.sqlite");
        CustomerControllerTest.customerController = new CustomerController();
    }

    @Test
    public void isPasswordInvalidTest() throws SQLException, NoSuchAlgorithmException {

        assertFalse(CustomerControllerTest.customerController.logIn("Test", " ", CustomerControllerTest.conn));
    }

    @Test
    public void isAddCustomerValidTest() throws SQLException, NoSuchAlgorithmException {

        CustomerControllerTest.customerController.createCustomer("Test", "Test", "Test", "Test", CustomerControllerTest.conn);

        assertTrue(CustomerControllerTest.customerController.logIn("Test", "Test", CustomerControllerTest.conn));
    }

    @Test
    public void isDeactivateCustomerValidTest() throws SQLException, NoSuchAlgorithmException {

        CustomerDaoImpl customerDao = new CustomerDaoImpl(CustomerControllerTest.conn);
        Customer customer = customerDao.find("Test");
        CustomerControllerTest.customerController.deactivateCustomer(customer, CustomerControllerTest.conn);
        customer = customerDao.find("Test");

        assertFalse(customer.isActive());
    }

    @Test
    public void isActivateCustomerValidTest() throws SQLException, NoSuchAlgorithmException {

        CustomerDaoImpl customerDao = new CustomerDaoImpl(CustomerControllerTest.conn);
        Customer customer = customerDao.find("Test");
        CustomerControllerTest.customerController.activateCustomer(customer, CustomerControllerTest.conn);
        customer = customerDao.find(customer.getId());

        assertTrue(customer.isActive());
    }






}
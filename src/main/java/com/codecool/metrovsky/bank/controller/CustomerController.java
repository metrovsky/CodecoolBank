package com.codecool.metrovsky.bank.controller;

import com.codecool.metrovsky.bank.dao.implementation.CustomerDaoImpl;
import com.codecool.metrovsky.bank.model.Customer;

import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;


public class CustomerController {

    public boolean logIn(String login, String password, Connection conn) throws NoSuchAlgorithmException, SQLException {
        CustomerDaoImpl customerDao = new CustomerDaoImpl(conn);
        Customer customer = customerDao.find(login);
        if (customer != null) {
            String hash = customer.createHash(password);
            if (customer.getPassword().equals(hash)) {
                customer.setLastLogin(new Timestamp(System.currentTimeMillis()));
                return true;
            }
        }
        return false;
    }

    public void createCustomer(String firstName, String lastName, String login, String password, Connection conn)
            throws SQLException, NoSuchAlgorithmException {
        CustomerDaoImpl customerDao = new CustomerDaoImpl(conn);
        Customer customer = new Customer(firstName, lastName, login, password, new Timestamp(System.currentTimeMillis()),
                true);

        customerDao.add(customer);
    }

    public void deactivateCustomer(Customer customer, Connection conn) throws SQLException {
        CustomerDaoImpl customerDao = new CustomerDaoImpl(conn);
        customerDao.deactivate(customer);
    }

    public void activateCustomer(Customer customer, Connection conn) throws SQLException {
        CustomerDaoImpl customerDao = new CustomerDaoImpl(conn);
        customerDao.activate(customer);
    }


}

package com.codecool.metrovsky.bank.dao;

import com.codecool.metrovsky.bank.model.Customer;

import java.sql.SQLException;

public interface CustomerDao {

    void add(Customer customer) throws SQLException;

    void deactivate(Customer customer) throws SQLException;

    void activate(Customer customer) throws SQLException;

    Customer find(Integer ID) throws SQLException;
}

package com.codecool.metrovsky.bank.dao.implementation;

import com.codecool.metrovsky.bank.dao.CustomerDao;
import com.codecool.metrovsky.bank.model.Customer;
import com.codecool.metrovsky.bank.model.Status;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerDaoImpl implements CustomerDao {

    private final Connection conn;

    public CustomerDaoImpl(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void add(Customer customer) throws SQLException {
        String query = "INSERT INTO Customers (FirstName, LastName, Login, " +
                "Password, CreateDate, IsActive, LastLogin) VALUES (?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement dbStatement = this.conn.prepareStatement(query);
        dbStatement.setString(1, customer.getFirstName());
        dbStatement.setString(2, customer.getLastName());
        dbStatement.setString(3, customer.getLogin());
        dbStatement.setString(4, customer.getPassword());
        dbStatement.setTimestamp(5, customer.getCreateDate());
        dbStatement.setBoolean(6, customer.isActive());
        dbStatement.setTimestamp(7, customer.getLastLogin());

        dbStatement.execute();
        dbStatement.close();
    }

    @Override
    public void activate(Customer customer) throws SQLException {
        String query = "UPDATE Customers SET IsActive = ? WHERE CustomerID = ?";
        PreparedStatement dbStatement = this.conn.prepareStatement(query);
        dbStatement.setBoolean(1, Status.OPEN.getValue());
        dbStatement.setInt(2, customer.getId());

        dbStatement.executeUpdate();
        dbStatement.close();
    }

    @Override
    public void deactivate(Customer customer) throws SQLException {
        String query = "UPDATE Customers SET IsActive = ? WHERE CustomerID = ?";
        PreparedStatement dbStatement = this.conn.prepareStatement(query);
        dbStatement.setBoolean(1, Status.CLOSED.getValue());
        dbStatement.setInt(2, customer.getId());

        dbStatement.executeUpdate();
        dbStatement.close();
    }

    @Override
    public Customer find(Integer ID) throws SQLException {
        String query = "SELECT * FROM Customers WHERE CustomerID = ?";
        Customer foundCustomer = null;
        PreparedStatement dbStatement = this.conn.prepareStatement(query);
        dbStatement.setInt(1, ID);

        ResultSet resultSet = dbStatement.executeQuery();
        if (resultSet.next()) {
            foundCustomer = new Customer(
                    resultSet.getInt("CustomerID"),
                    resultSet.getString("FirstName"),
                    resultSet.getString("LastName"),
                    resultSet.getString("Login"),
                    resultSet.getString("Password"),
                    resultSet.getTimestamp("CreateDate"),
                    resultSet.getBoolean("IsActive"),
                    resultSet.getTimestamp("LastLogin")
            );
        }

        dbStatement.close();
        return foundCustomer;
    }


    public Customer find(String login) throws SQLException {
        String query = "SELECT * FROM Customers WHERE Login = ?";
        Customer foundCustomer = null;

        PreparedStatement dbStatement = conn.prepareStatement(query);
        dbStatement.setString(1, login);

        ResultSet resultSet = dbStatement.executeQuery();
        if (resultSet.next()) {
            foundCustomer = new Customer(
                    resultSet.getInt("CustomerID"),
                    resultSet.getString("FirstName"),
                    resultSet.getString("LastName"),
                    resultSet.getString("Login"),
                    resultSet.getString("Password"),
                    resultSet.getTimestamp("CreateDate"),
                    resultSet.getBoolean("IsActive"),
                    resultSet.getTimestamp("LastLogin")
            );
        }

        dbStatement.close();
        return foundCustomer;
    }

}

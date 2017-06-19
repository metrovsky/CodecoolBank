package com.codecool.metrovsky.bank.dao.implementation;

import com.codecool.metrovsky.bank.dao.TransactionStatusDao;
import com.codecool.metrovsky.bank.model.TransactionStatus;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TransactionStatusDaoImpl implements TransactionStatusDao {

    private Connection conn;

    public TransactionStatusDaoImpl(Connection conn) {
        this.conn = conn;
    }

    @Override
    public TransactionStatus find(Integer ID) throws SQLException {
        String query = "SELECT * FROM TransactionTypes WHERE TransactionTypeID = ?";
        TransactionStatus foundStatus = null;

        PreparedStatement dbStatement = conn.prepareStatement(query);
        dbStatement.setInt(1, ID);

        ResultSet resultSet = dbStatement.executeQuery();
        if (resultSet.next()) {
            foundStatus = new TransactionStatus(
                    resultSet.getInt(1),
                    resultSet.getString(2),
                    resultSet.getString(3));
        }
        dbStatement.close();
        return foundStatus;
    }
}

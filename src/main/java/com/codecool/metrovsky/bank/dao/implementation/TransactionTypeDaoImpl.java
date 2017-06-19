package com.codecool.metrovsky.bank.dao.implementation;

import com.codecool.metrovsky.bank.dao.TransactionTypeDao;
import com.codecool.metrovsky.bank.model.TransactionType;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TransactionTypeDaoImpl implements TransactionTypeDao {

    private Connection conn;

    public TransactionTypeDaoImpl(Connection conn) {
        this.conn = conn;
    }

    @Override
    public TransactionType find(Integer ID) throws SQLException {
        String query = "SELECT * FROM TransactionTypes WHERE TransactionTypeID = ?";
        TransactionType foundType = null;

        PreparedStatement dbStatement = conn.prepareStatement(query);
        dbStatement.setInt(1, ID);

        ResultSet resultSet = dbStatement.executeQuery();
        if (resultSet.next()) {
            foundType = new TransactionType(
                    resultSet.getInt(1),
                    resultSet.getString(2),
                    resultSet.getString(3));
        }
        dbStatement.close();
        return foundType;
    }
}

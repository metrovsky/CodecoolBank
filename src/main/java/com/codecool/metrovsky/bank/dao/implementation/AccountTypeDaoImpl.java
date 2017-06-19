package com.codecool.metrovsky.bank.dao.implementation;

import com.codecool.metrovsky.bank.dao.AccountTypeDao;
import com.codecool.metrovsky.bank.model.AccountType;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountTypeDaoImpl implements AccountTypeDao {

    private Connection conn;

    AccountTypeDaoImpl(Connection conn) {
        this.conn = conn;
    }

    @Override
    public AccountType find(Integer ID) throws SQLException {
        String query = "SELECT * FROM AccountTypes WHERE AccountTypeID = ?";
        AccountType foundType = null;

        PreparedStatement dbStatement = conn.prepareStatement(query);
        dbStatement.setInt(1, ID);

        ResultSet resultSet = dbStatement.executeQuery();
        if (resultSet.next()) {
            foundType = new AccountType(
                    resultSet.getInt(1),
                    resultSet.getString(2),
                    resultSet.getString(3));
        }

        dbStatement.close();
        return foundType;
    }
}

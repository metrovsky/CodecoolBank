package com.codecool.metrovsky.bank.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnector {

    private final String PATH = "jdbc:sqlite:src/resources/database/metrovskybank.sqlite";

    public Connection connect() throws SQLException {
        Connection conn;
        try {
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection(PATH);
        } catch (Exception e) {
            throw new SQLException("Couldn't connect to database!");
        }
        return conn;
    }
}
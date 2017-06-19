package com.codecool.metrovsky.bank.dao;

import com.codecool.metrovsky.bank.model.TransactionStatus;

import java.sql.SQLException;

public interface TransactionStatusDao {

    TransactionStatus find(Integer ID) throws SQLException;
}

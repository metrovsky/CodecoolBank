package com.codecool.metrovsky.bank.dao;

import com.codecool.metrovsky.bank.model.TransactionType;

import java.sql.SQLException;

public interface TransactionTypeDao {

    TransactionType find(Integer ID) throws SQLException;

}

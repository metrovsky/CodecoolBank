package com.codecool.metrovsky.bank.dao;

import com.codecool.metrovsky.bank.model.AccountType;

import java.sql.SQLException;

public interface AccountTypeDao {

    AccountType find(Integer ID) throws SQLException;
}

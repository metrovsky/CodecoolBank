package com.codecool.metrovsky.bank.dao;

import com.codecool.metrovsky.bank.model.Account;
import com.codecool.metrovsky.bank.model.Transaction;
import com.codecool.metrovsky.bank.model.TransactionStatus;

import java.sql.SQLException;
import java.util.List;

public interface TransactionDao {

    void add(Transaction transaction) throws SQLException;

    void updateStatus(Transaction transaction, TransactionStatus status) throws SQLException;

    List<Transaction> findBy(Account account) throws SQLException;
}

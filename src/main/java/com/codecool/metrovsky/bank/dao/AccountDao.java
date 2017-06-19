package com.codecool.metrovsky.bank.dao;

import com.codecool.metrovsky.bank.model.Account;
import com.codecool.metrovsky.bank.model.Customer;

import java.sql.SQLException;
import java.util.List;

public interface AccountDao {

    void add(Account account) throws SQLException;
    Account find(Integer ID) throws SQLException;
    List<Account> findBy(Customer customer) throws SQLException;
    List<String> getAllNumbers() throws SQLException;
    void block(Account account) throws SQLException;
    void unblock(Account account) throws SQLException;
    void deposit(Account account, Long amount) throws SQLException;
    void withdraw(Account account, Long amount) throws SQLException;
}


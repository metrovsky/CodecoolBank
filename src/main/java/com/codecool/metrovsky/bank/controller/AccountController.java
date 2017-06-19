package com.codecool.metrovsky.bank.controller;

import com.codecool.metrovsky.bank.dao.implementation.AccountDaoImpl;
import com.codecool.metrovsky.bank.model.Account;
import com.codecool.metrovsky.bank.model.AccountType;
import com.codecool.metrovsky.bank.model.Customer;

import java.math.BigInteger;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;
import java.util.Random;


public class AccountController {

    private String randomNumber() {
        Random r = new Random();
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < 16; i++) {
            int sign = r.nextInt(10);
            stringBuilder.append(sign);
        }
        return stringBuilder.toString();
    }

    private boolean numberExists(List<String> numbers, String newNumber) {
        for (String number : numbers) {
            if (number.equals(newNumber)) {
                return true;
            }
        }
        return false;
    }

    private String generateNumber(Connection conn) throws SQLException {
        AccountDaoImpl accountDao = new AccountDaoImpl(conn);
        List<String> numbers = accountDao.getAllNumbers();
        String number = this.randomNumber();
        while (this.numberExists(numbers, number)) {
            number = this.randomNumber();
        }
        return number;
    }

    public void createAccount(Customer customer, AccountType type, BigInteger input, BigInteger debitLine, int interest,
                              Connection conn) throws SQLException {
        AccountDaoImpl accountDao = new AccountDaoImpl(conn);
        Account account = new Account(customer, this.generateNumber(conn), 1,
                type, new Timestamp(System.currentTimeMillis()), input, debitLine, interest);
        accountDao.add(account);
    }

    public void blockAccount(Account account, Connection conn) throws SQLException {
        AccountDaoImpl accountDao = new AccountDaoImpl(conn);
        accountDao.block(account);
    }

    public void unblockAccount(Account account, Connection conn) throws SQLException {
        AccountDaoImpl accountDao = new AccountDaoImpl(conn);
        accountDao.unblock(account);
    }

    public void deposit(Account account, Long amount, Connection conn) throws SQLException, UnsupportedOperationException {
        AccountDaoImpl accountDao = new AccountDaoImpl(conn);
        if (account.getStatus() == 1) {
            accountDao.deposit(account, amount);
        } else {
            throw new UnsupportedOperationException("This account is inactive");
        }
    }

    public void withdraw(Account account, Long amount, Connection conn) throws SQLException, UnsupportedOperationException {
        AccountDaoImpl accountDao = new AccountDaoImpl(conn);
        if (this.isValid(account, amount)) {
            accountDao.withdraw(account, amount);
        } else {
            throw new UnsupportedOperationException("Can't withdraw this amount");
        }
    }

    public List<Account> displayCustomersAccounts(Customer customer, Connection conn) throws SQLException {
        AccountDaoImpl accountDao = new AccountDaoImpl(conn);
        return accountDao.findBy(customer);
    }

    public boolean isValid(Account account, Long amount) {
        return account.getBalance().add(account.getDebitLine()).compareTo(BigInteger.valueOf(amount)) >=
                0 && account.getStatus() == 1;
    }
}

package com.codecool.metrovsky.bank.model;

import java.math.BigInteger;
import java.sql.Timestamp;

public class Account extends BaseModel {

    private final Customer customer;
    private final String number;
    private final Timestamp openDate;
    private Integer status;
    private AccountType type;
    private BigInteger balance;
    private BigInteger debitLine;
    private Integer interest;

    public Account(Integer id, Customer customer, String number, Timestamp openDate, Integer status,
                   AccountType type, BigInteger balance, BigInteger debitLine, Integer interest) {
        super(id);
        this.customer = customer;
        this.number = number;
        this.openDate = openDate;
        this.status = status;
        this.type = type;
        this.balance = balance;
        this.debitLine = debitLine;
        this.interest = interest;
    }

    public Account(Customer customer, String number, Integer status, AccountType type,
                   Timestamp openDate, BigInteger balance, BigInteger debitLine, Integer interest) {
        this.customer = customer;
        this.number = number;
        this.openDate = openDate;
        this.status = status;
        this.type = type;
        this.balance = balance;
        this.debitLine = debitLine;
        this.interest = interest;
    }

    public Customer getCustomer() {
        return customer;
    }

    public String getNumber() {
        return number;
    }

    public Timestamp getOpenDate() {
        return openDate;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public BigInteger getBalance() {
        return balance;
    }

    public void setBalance(BigInteger balance) {
        this.balance = balance;
    }

    public BigInteger getDebitLine() {
        return debitLine;
    }

    public void setDebitLine(BigInteger debitLine) {
        this.debitLine = debitLine;
    }

    public Integer getInterest() {
        return interest;
    }

    public void setInterest(Integer interest) {
        this.interest = interest;
    }

    public AccountType getType() {
        return type;
    }

    public void setType(AccountType type) {
        this.type = type;
    }
}

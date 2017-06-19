package com.codecool.metrovsky.bank.model;

import org.junit.jupiter.api.Test;

import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


class AccountTest {

    @Test
    public void isGetCustomerValid() throws NoSuchAlgorithmException {
        Customer customer = new Customer(null, null, null, "Test", null,
                false);
        Account savingAccount = new Account(customer, null, null, null, null, null,
                null, 0);
        assertEquals(customer, savingAccount.getCustomer());
    }

    @Test
    public void isGetNumberValid() {
        Account creditAccount = new Account(null, "123456", null, null, null,
                null, null, 0);
        assertEquals("123456", creditAccount.getNumber());
    }

    @Test
    public void isSetStatusValid() {
        Account creditAccount = new Account(null, null, 1, null, null,
                null, null, 0);
        creditAccount.setStatus(1);
        assertTrue(1 == creditAccount.getStatus());
    }

    @Test
    public void isGetOpenDateValid() {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        Account creditAccount = new Account(null, null, null, null, timestamp,
                null, null, 0);
        assertEquals(timestamp, creditAccount.getOpenDate());
    }

    @Test
    public void isSetBalanceValid() {
        Account creditAccount = new Account(null, null, null, null, null,
                null, null, 0);
        creditAccount.setBalance(BigInteger.valueOf(999999999));
        assertEquals(BigInteger.valueOf(999999999), creditAccount.getBalance());
    }

    @Test
    public void isSetDebitLineValid() {
        Account creditAccount = new Account(null, null, null, null, null,
                null, null, 0);
        creditAccount.setDebitLine(BigInteger.valueOf(100));
        assertEquals(BigInteger.valueOf(100), creditAccount.getDebitLine());
    }

    @Test
    public void isSetInterestValid() {
        Account creditAccount = new Account(null, null, null, null, null,
                null, null, 0);
        creditAccount.setInterest(100);
        assertTrue(100 == creditAccount.getInterest());
    }


}
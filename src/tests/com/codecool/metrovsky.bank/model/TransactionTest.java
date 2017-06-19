package com.codecool.metrovsky.bank.model;

import org.junit.jupiter.api.Test;

import java.math.BigInteger;
import java.sql.Timestamp;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


class TransactionTest {

    @Test
    public void isIdGetterValid() {
        Transaction transaction = new Transaction(0, null, null, BigInteger.valueOf(0),
                "Test", null, null, null, null);
        assertTrue(0 == transaction.getId());
    }

    @Test
    public void isTransactionDateGetterValid() {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        Transaction transaction = new Transaction(timestamp, null, BigInteger.valueOf(0),
                "Test", null, null, null, null);
        assertEquals(timestamp, transaction.getTransactionDate());
    }

    @Test
    public void isTypeGetterValid() {
        TransactionType transactionType = new TransactionType("Test", "Test");
        Transaction transaction = new Transaction(null, transactionType, null,
                "Test", null, null, null, null);
        assertEquals(transactionType, transaction.getType());
    }

    @Test
    public void isValueGetterValid() {
        BigInteger bigInteger = BigInteger.valueOf(1);
        Transaction transaction = new Transaction(null, null, null, BigInteger.valueOf(1),
                "Test", null, null, null, null);
        assertEquals(bigInteger, transaction.getValue());
    }

    @Test
    public void isDescriptionGetterValid() {
        Transaction transaction = new Transaction(null, null, null, BigInteger.valueOf(0),
                "Test", null, null, null, null);
        assertEquals("Test", transaction.getDescription());
    }

    @Test
    public void isStatusGetterValid() {
        TransactionStatus transactionStatus = new TransactionStatus("Test", "Test");
        Transaction transaction = new Transaction(null, null, null, BigInteger.valueOf(0),
                "Test", transactionStatus, null, null, null);
        assertEquals(transactionStatus, transaction.getStatus());
    }

    @Test
    public void isSourceAccuntGetterValid() {
        Account sourceAccount = new Account(1, null, null, null, null, null, null,
                null, 0);
        Transaction transaction = new Transaction(null, null, null, BigInteger.valueOf(0),
                "Test", null, sourceAccount, null, null);
        assertEquals(sourceAccount, transaction.getSourceAccount());
    }

    @Test
    public void isSourceCardIdGetterValid() {
        Transaction transaction = new Transaction(null, null, null, BigInteger.valueOf(0),
                "Test", null, null, 1, null);
        assertTrue(1 == transaction.getSourceCardID());
    }

    @Test
    public void isDestinationAccuntGetterValid() {
        Account destinationAccount = new Account(null, null, null, null, null, null,
                null, 0);
        Transaction transaction = new Transaction(null, null, null, BigInteger.valueOf(0),
                "Test", null, null, null, destinationAccount);
        assertEquals(destinationAccount, transaction.getDestinationAccount());
    }


}
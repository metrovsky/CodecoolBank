package com.codecool.metrovsky.bank.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


class TypeModelTest {

    @Test
    public void isGetNameValid() {
        TransactionStatus transactionStatus = new TransactionStatus(0, "Test", "Test");
        assertEquals("Test", transactionStatus.getName());
    }

    @Test
    public void isGetDescriptionValid() {
        TransactionType transactionType = new TransactionType(0, "Test", "Testcription");
        assertEquals("Testcription", transactionType.getDescription());
    }

}
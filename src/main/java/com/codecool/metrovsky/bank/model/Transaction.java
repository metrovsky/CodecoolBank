package com.codecool.metrovsky.bank.model;

import java.math.BigInteger;
import java.sql.Timestamp;

public class Transaction extends BaseModel {

    private final Timestamp transactionDate;
    private final TransactionType type;
    private final BigInteger value;
    private final String description;
    private final TransactionStatus status;
    private final Account sourceAccount;
    private final Integer sourceCardID;
    private final Account destinationAccount;

    public Transaction(Timestamp transactionDate, TransactionType type, BigInteger value,
                       String description, TransactionStatus status, Account sourceAccount, Integer sourceCardID,
                       Account destinationAccount) {
        this.transactionDate = transactionDate;
        this.type = type;
        this.value = value;
        this.description = description;
        this.status = status;
        this.sourceAccount = sourceAccount;
        this.sourceCardID = sourceCardID;
        this.destinationAccount = destinationAccount;
    }

    public Transaction(Integer id, Timestamp transactionDate, TransactionType type, BigInteger value,
                       String description, TransactionStatus status, Account sourceAccount, Integer sourceCardID,
                       Account destinationAccount) {
        super(id);
        this.transactionDate = transactionDate;
        this.type = type;
        this.value = value;
        this.description = description;
        this.status = status;
        this.sourceAccount = sourceAccount;
        this.sourceCardID = sourceCardID;
        this.destinationAccount = destinationAccount;
    }

    public Timestamp getTransactionDate() {
        return transactionDate;
    }

    public TransactionType getType() {
        return type;
    }

    public TransactionStatus getStatus() {
        return status;
    }

    public Account getSourceAccount() {
        return sourceAccount;
    }

    public Account getDestinationAccount() {
        return destinationAccount;
    }

    public BigInteger getValue() {
        return value;
    }

    public String getDescription() {
        return description;
    }


    public Integer getSourceCardID() {
        return sourceCardID;
    }

}




package com.codecool.metrovsky.bank.model;

public class TransactionType extends TypeModel {

    public TransactionType(String name, String description) {
        super(name, description);
    }

    public TransactionType(Integer id, String name, String description) {
        super(id, name, description);
    }

    public String getDescription() {
        return description;
    }
}

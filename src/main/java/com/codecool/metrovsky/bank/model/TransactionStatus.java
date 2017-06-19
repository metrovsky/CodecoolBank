package com.codecool.metrovsky.bank.model;


public class TransactionStatus extends TypeModel {

    public TransactionStatus(String name, String description) {
        super(name, description);
    }

    public TransactionStatus(Integer id, String name, String description) {
        super(id, name, description);
    }

    public String getName() {
        return name;
    }
}

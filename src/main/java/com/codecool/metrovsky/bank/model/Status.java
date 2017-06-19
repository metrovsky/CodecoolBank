package com.codecool.metrovsky.bank.model;

public enum Status {
    OPEN(true),
    CLOSED(false);

    private Boolean status;

    Status(Boolean status) {
        this.status = status;
    }

    public Boolean getValue() {
        return status;
    }

}

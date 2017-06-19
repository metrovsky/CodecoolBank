package com.codecool.metrovsky.bank.model;

abstract class BaseModel {
    private Integer id;

    BaseModel() {
    }

    BaseModel(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }
}



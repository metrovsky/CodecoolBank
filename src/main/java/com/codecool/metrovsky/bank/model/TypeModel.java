package com.codecool.metrovsky.bank.model;

abstract class TypeModel extends BaseModel {

    protected final String name;
    protected final String description;

    TypeModel(Integer id, String name, String description) {
        super(id);
        this.name = name;
        this.description = description;
    }

    TypeModel(String name, String description) {
        this.name = name;
        this.description = description;
    }
}

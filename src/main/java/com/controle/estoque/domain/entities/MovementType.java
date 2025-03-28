package com.controle.estoque.domain.entities;

public enum MovementType {

    INPUT(1, "Entrada"),
    EXIT(2,"Saída");

    private final Integer type;
    private final String name;

    MovementType(Integer type, String name) {
        this.type = type;
        this.name = name;
    }
}

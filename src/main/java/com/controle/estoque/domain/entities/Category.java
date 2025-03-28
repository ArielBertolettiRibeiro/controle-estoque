package com.controle.estoque.domain.entities;

public enum Category {

    ELETRONICS(1, "Eletronicos"),
    FOODS(2, "Alimentos"),
    SPORTS(3, "Esportes"),
    LEISURE(4, "Lazer");

    private final Integer number;
    private final String name;

    Category(Integer number, String name) {
        this.number = number;
        this.name = name;
    }
}

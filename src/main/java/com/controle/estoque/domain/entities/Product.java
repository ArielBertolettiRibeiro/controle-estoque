package com.controle.estoque.domain.entities;

import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;
import java.util.List;

@Data
@Entity
@Table(name = "produtos")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome", nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "categoria", nullable = false)
    private Category category;

    @Column(name = "valor_unidade", nullable = false)
    private BigDecimal unitPrice;

    @Column(name = "quantidade_disponivel", nullable = false)
    private Integer availableQuantity;

    @OneToMany(mappedBy = "produto", cascade = CascadeType.ALL)
    private List<Sale> sales;

    @OneToMany(mappedBy = "produto", cascade = CascadeType.ALL)
    private List<StockMovement> stockMovements;

    public Product() {
    }

    public Product(String name, Category category, BigDecimal unitPrice, Integer availableQuantity) {
        this.name = name;
        this.category = category;
        this.unitPrice = unitPrice;
        this.availableQuantity = availableQuantity;
    }
}

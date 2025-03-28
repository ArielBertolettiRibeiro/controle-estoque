package com.controle.estoque.domain.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
@Entity
@Table(name = "vendas")
public class Sale {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "quantidade_vendida", nullable = false)
    private Integer quantitySold;

    @Column(name = "data_venda", updatable = false)
    private LocalDateTime saleDate = LocalDateTime.now();

    @ManyToOne
    @JoinColumn(name = "produto_id", nullable = false)
    private Product product;

    public Sale() {
    }

    public Sale(Integer quantitySold, Product product) {
        this.quantitySold = quantitySold;
        this.saleDate = LocalDateTime.now();
        this.product = product;
    }


}

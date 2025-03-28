package com.controle.estoque.domain.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity
@Table(name = "movimentacoes_estoque")
public class StockMovement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "produto_id", nullable = false)
    private Product product;

    @Column(name = "quantidade_movimentada", nullable = false)
    private int quantityMoved;

    @Enumerated(EnumType.STRING)
    private MovementType type;

    @Column(name = "data_movimentacao", nullable = false, updatable = false)
    private LocalDateTime movementDate = LocalDateTime.now();

    public StockMovement(Product product, int quantityMoved, MovementType type) {
        this.product = product;
        this.quantityMoved = quantityMoved;
        this.type = type;
        this.movementDate = LocalDateTime.now();
    }
}

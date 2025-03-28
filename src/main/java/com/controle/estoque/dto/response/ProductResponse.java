package com.controle.estoque.dto.response;

import com.controle.estoque.domain.entities.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductResponse {

    private Long id;
    private String nome;
    private Category categoria;
    private BigDecimal preco;
    private Integer quantidadeDisponivel;
}

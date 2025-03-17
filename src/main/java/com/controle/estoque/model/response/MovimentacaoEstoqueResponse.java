package com.controle.estoque.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class MovimentacaoEstoqueResponse {
    private Long id;
    private Long produtoId;
    private String produtoNome;
    private Integer quantidadeMovimentada;
    private String tipo;
    private LocalDateTime dataMovimentacao;
}

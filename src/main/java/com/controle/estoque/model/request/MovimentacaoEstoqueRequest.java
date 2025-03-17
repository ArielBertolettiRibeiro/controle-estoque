package com.controle.estoque.model.request;

import com.controle.estoque.model.domain.entities.TipoMovimentacao;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class MovimentacaoEstoqueRequest {

    private Long produtoId;
    private Integer quantidadeMovimentada;
    private TipoMovimentacao tipo;
    private LocalDateTime dataMovimentacao = LocalDateTime.now();;
}

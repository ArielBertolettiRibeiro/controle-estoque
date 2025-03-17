package com.controle.estoque.model.response;

import com.controle.estoque.model.domain.entities.Categoria;
import com.controle.estoque.model.request.MovimentacaoEstoqueRequest;
import com.controle.estoque.model.request.VendaRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProdutoResponse {

    private Long id;
    private String nome;
    private Categoria categoria;
    private BigDecimal preco;
    private Integer quantidadeDeisponivel;
}

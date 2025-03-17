package com.controle.estoque.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class VendaResponse {

    private Long id;
    private Integer quantidadeVendida;
    private LocalDateTime dataVenda = LocalDateTime.now();
    private Long produtoId;
    private String nomeProduto;
}

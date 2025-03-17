package com.controle.estoque.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class VendaRequest {

    private Long produtoId;
    private Integer quantidadeVendida;
}

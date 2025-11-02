package com.estoqueApp.estoqueApp.dto.EntradaEstoque;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class EntradaEstoqueDto extends EntradaEstoqueCreateDto{

    private Integer idEntradaEstoque;

    private String nomeProduto;

    private Integer quantidade;

    private LocalDateTime dataEntrada;
}

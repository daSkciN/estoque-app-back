package com.estoqueApp.estoqueApp.dto.EntradaEstoque;

import com.estoqueApp.estoqueApp.entity.Produto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class EntradaEstoqueCreateDto {

    @NotNull(message = "O ID do produto é obrigatório.")
    private Integer idProduto;

    @NotNull(message = "A quantidade é obrigatória.")
    private Integer quantidade;
}

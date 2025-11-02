package com.estoqueApp.estoqueApp.dto.ItemVenda;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ItemVendaCreateDto {

    @NotNull(message = "O ID do produto é obrigatório.")
    private Integer idProduto;

    @NotNull(message = "A quantidade é obrigatória.")
    private Integer quantidade;

    @NotNull(message = "O preço unitário é obrigatório.")
    private BigDecimal precoUnitario;

}

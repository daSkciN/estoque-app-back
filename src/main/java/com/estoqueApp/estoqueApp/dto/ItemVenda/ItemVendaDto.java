package com.estoqueApp.estoqueApp.dto.ItemVenda;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ItemVendaDto {

    private Integer idItemVenda;

    private Integer idProduto;

    private String nomeProduto;

    private Integer quantidade;

    private BigDecimal precoUnitario;

}

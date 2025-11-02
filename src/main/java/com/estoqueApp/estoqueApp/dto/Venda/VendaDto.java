package com.estoqueApp.estoqueApp.dto.Venda;

import com.estoqueApp.estoqueApp.dto.ItemVenda.ItemVendaDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class VendaDto {

    private Integer idVenda;

    private LocalDateTime data;

    private BigDecimal valorTotal;

    private List<ItemVendaDto> itens;
}

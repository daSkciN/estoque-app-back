package com.estoqueApp.estoqueApp.dto.Venda;

import com.estoqueApp.estoqueApp.dto.ItemVenda.ItemVendaCreateDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class VendaCreateDto {

    @NotEmpty(message = "A lista de itens não pode estar vazia.")
    private List<ItemVendaCreateDto> itens;

}

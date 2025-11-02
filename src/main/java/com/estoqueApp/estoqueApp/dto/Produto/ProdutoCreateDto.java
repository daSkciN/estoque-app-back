package com.estoqueApp.estoqueApp.dto.Produto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ProdutoCreateDto {

    @NotBlank(message = "O nome do produto é obrigatório.")
    private String nome;

    @NotBlank(message = "O nome da categoria é obrigatório.")
    private String nomeCategoria;

    @NotNull(message = "O preço de custo do produto é obrigatório.")
    private BigDecimal precoCusto;

    @NotNull(message = "O preço de venda do produto é obrigatório.")
    private BigDecimal precoVenda;

}

package com.estoqueApp.estoqueApp.dto.Produto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ProdutoDto extends ProdutoCreateDto{

    private Integer idProduto;

    private String nome;

    private BigDecimal precoCusto;

    private BigDecimal precoVenda;

    private Integer quantidadeEstoque;

    private String nomeCategoria;

}

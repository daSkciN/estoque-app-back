package com.estoqueApp.estoqueApp.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Getter
@Setter
@Table(name = "produto")
@AllArgsConstructor
@NoArgsConstructor
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_id_produto")
    @SequenceGenerator(name = "seq_id_produto", sequenceName = "seq_id_produto", allocationSize = 1)
    @Column(name = "id_produto")
    private Integer idProduto;

    @JoinColumn(name = "nome")
    private String nome;

    @JoinColumn(name = "preco_custo")
    private BigDecimal precoCusto;

    @JoinColumn(name = "preco_venda")
    private BigDecimal precoVenda;

    @Column(name = "quantidade_estoque")
    private Integer quantidadeEstoque = 0;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "categoria_id")
    private Categoria categoria;

}

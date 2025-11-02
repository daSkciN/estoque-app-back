package com.estoqueApp.estoqueApp.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "itemVenda")
public class ItemVenda {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_id_item_venda")
    @SequenceGenerator(name = "seq_id_item_venda", sequenceName = "seq_id_item_venda", allocationSize = 1)
    @Column(name = "id_item_venda")
    private Integer idItemVenda;

    @ManyToOne
    @JoinColumn(name = "id_venda")
    @JsonBackReference
    private Venda venda;

    @ManyToOne
    @JoinColumn(name = "id_produto")
    private Produto produto;

    @Column(name = "quantidade")
    private Integer quantidade;

    @Column(name = "preco_unitario")
    private BigDecimal precoUnitario;

}

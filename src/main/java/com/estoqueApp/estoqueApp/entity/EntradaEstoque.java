package com.estoqueApp.estoqueApp.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EntradaEstoque {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_id_entrada_estoque")
    @SequenceGenerator(name = "seq_id_entrada_estoque", sequenceName = "seq_id_entrada_estoque", allocationSize = 1)
    @Column(name = "id_entrada_estoque")
    private Integer idEntradaEstoque;

    private LocalDateTime data;

    @ManyToOne
    @JoinColumn(name = "id_produto")
    private Produto produto;

    private Integer quantidade;

}

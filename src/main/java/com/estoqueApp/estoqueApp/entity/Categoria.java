package com.estoqueApp.estoqueApp.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "categoria")
@AllArgsConstructor
@NoArgsConstructor
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_id_categoria")
    @SequenceGenerator(name = "seq_id_categoria", sequenceName = "seq_id_categoria", allocationSize = 1)
    @JoinColumn(name = "id_categoria")
    private Integer idCategoria;

    private String nome;
}

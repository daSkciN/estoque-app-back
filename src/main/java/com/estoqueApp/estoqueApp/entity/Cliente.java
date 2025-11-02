package com.estoqueApp.estoqueApp.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Cliente {

    @Id
    private Integer idCliente;

    private String nome;

    private String cpf;

    private String email;

    private String telefone;

    private String endereco;

}

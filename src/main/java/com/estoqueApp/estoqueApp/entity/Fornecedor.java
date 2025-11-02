package com.estoqueApp.estoqueApp.entity;


import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Fornecedor {

    @Id
    private Integer idFornecedor;

    private String nome;

    private String contato;

    private String endereco;

}

package com.estoqueApp.estoqueApp.repository;

import com.estoqueApp.estoqueApp.entity.EntradaEstoque;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EntradaEstoqueRepository extends JpaRepository<EntradaEstoque, Integer> {

}

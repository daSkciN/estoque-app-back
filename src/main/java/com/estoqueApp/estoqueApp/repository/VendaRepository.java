package com.estoqueApp.estoqueApp.repository;

import com.estoqueApp.estoqueApp.entity.Venda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VendaRepository extends JpaRepository<Venda, Integer> {

}

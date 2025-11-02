package com.estoqueApp.estoqueApp.repository;

import com.estoqueApp.estoqueApp.entity.ItemVenda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemVendaRepository extends JpaRepository<ItemVenda, Integer> {

}

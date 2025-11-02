package com.estoqueApp.estoqueApp.repository;

import com.estoqueApp.estoqueApp.entity.Categoria;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {

    Optional<Categoria> findByNome(@NotBlank(message = "O nome da categoria é obrigatório.") String nomeCategoria);
}

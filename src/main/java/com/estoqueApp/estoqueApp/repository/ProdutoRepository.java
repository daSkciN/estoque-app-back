package com.estoqueApp.estoqueApp.repository;

import com.estoqueApp.estoqueApp.entity.Produto;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Integer> {

    boolean existsByNomeIgnoreCase(@NotBlank(message = "O nome do produto é obrigatório.") String nome);

    boolean existsByNomeIgnoreCaseAndIdProdutoNot(@NotBlank(message = "O nome do produto é obrigatório.") String nome, Integer idProduto);

    @Query("SELECT COUNT(p) FROM Produto p WHERE p.quantidadeEstoque < :limite")
    Long countProdutosComEstoqueBaixo(@Param("limite") Integer limite);
}

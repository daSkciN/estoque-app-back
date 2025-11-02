package com.estoqueApp.estoqueApp.controller;

import com.estoqueApp.estoqueApp.dto.Produto.ProdutoCreateDto;
import com.estoqueApp.estoqueApp.dto.Produto.ProdutoDto;
import com.estoqueApp.estoqueApp.exception.RegraDeNegocioException;
import com.estoqueApp.estoqueApp.service.ProdutoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.tags.Tag;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/produto")
@Tag(name = "Produto", description = "Endpoints para gerenciamento de produtos")
public class ProdutoController {

    private final ProdutoService produtoService;

    @GetMapping
    public ResponseEntity<List<ProdutoDto>> findAll() throws RegraDeNegocioException {
        log.info("Listando produtos");
        List<ProdutoDto> produtos = produtoService.findAll();
        log.info("Produtos listados com sucesso!");
        return new ResponseEntity<>(produtos, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProdutoDto> findById(@PathVariable Integer id) throws RegraDeNegocioException {
        log.info("Listando um produto");
        ProdutoDto produto = produtoService.findById(id);
        log.info("Produto listado com sucesso!");
        return new ResponseEntity<>(produto, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ProdutoDto> create(@Valid @RequestBody ProdutoCreateDto produtoCreateDto) throws RegraDeNegocioException {

        log.info("Criando um produto");
        try {
            ProdutoDto produto = produtoService.create(produtoCreateDto);
            log.info("Produto criado com sucesso!");

            return new ResponseEntity<>(produto, HttpStatus.CREATED);
        }
        catch (Exception e){
            log.error("Erro ao criar produto: {}", e.getMessage(), e);
            throw e;
        }
    }


    @PutMapping("/{id}")
    public ResponseEntity<ProdutoDto> update(@PathVariable Integer id, @Valid @RequestBody ProdutoCreateDto produtoCreateDto) throws RegraDeNegocioException {
        log.info("Atualizando um produto");
        ProdutoDto produto = produtoService.update(id, produtoCreateDto);
        log.info("Produto atualizado com sucesso!");

        return new ResponseEntity<>(produto, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Integer id) throws Exception {
        log.info("Deletando um produto");
        produtoService.delete(id);
        log.info("Produto deletado com sucesso!");
        return ResponseEntity.ok("Produto removido com sucesso!");
    }
}

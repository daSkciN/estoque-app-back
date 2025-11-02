package com.estoqueApp.estoqueApp.controller;

import com.estoqueApp.estoqueApp.dto.EntradaEstoque.EntradaEstoqueCreateDto;
import com.estoqueApp.estoqueApp.dto.EntradaEstoque.EntradaEstoqueDto;

import com.estoqueApp.estoqueApp.exception.RegraDeNegocioException;
import com.estoqueApp.estoqueApp.service.EntradaEstoqueService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/entradaEstoque")
@Tag(name = "EntradaEstoque", description = "Endpoints para gerenciamento de entrada no estoque")
public class EntradaEstoqueController {

    private final EntradaEstoqueService entradaEstoqueService;

    @GetMapping
    public ResponseEntity<List<EntradaEstoqueDto>> findAll() throws RegraDeNegocioException {
        log.info("Buscando todas as entradas de estoque");
        List<EntradaEstoqueDto> entradaEstoqueDtos = entradaEstoqueService.findAll();
        log.info("Entradas de estoque listadas com sucesso!");
        return new ResponseEntity<>(entradaEstoqueDtos, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntradaEstoqueDto> findById(@PathVariable Integer id) throws RegraDeNegocioException {
        log.info("Buscando uma entrada de estoque");
        EntradaEstoqueDto entradaEstoqueDto = entradaEstoqueService.findById(id);
        log.info("Entrada de estoque listada com sucesso!");
        return new ResponseEntity<>(entradaEstoqueDto, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<EntradaEstoqueDto> create(@Valid @RequestBody EntradaEstoqueCreateDto entradaEstoqueCreateDto) throws RegraDeNegocioException {
        log.info("Criando uma entrada de estoque");
        EntradaEstoqueDto entradaEstoqueDto = entradaEstoqueService.create(entradaEstoqueCreateDto);
        log.info("Entrada de estoque criada com sucesso!");

        return new ResponseEntity<>(entradaEstoqueDto, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Integer id) throws RegraDeNegocioException {
        log.info("Deletando uma entrada de estoque");
        entradaEstoqueService.delete(id);
        log.info("Entrada de estoque deletada com sucesso!");
        return ResponseEntity.ok("Entrada de estoque removida com sucesso!");
    }

}

package com.estoqueApp.estoqueApp.controller;

import com.estoqueApp.estoqueApp.dto.Categoria.CategoriaCreateDto;
import com.estoqueApp.estoqueApp.dto.Categoria.CategoriaDto;
import com.estoqueApp.estoqueApp.exception.RegraDeNegocioException;
import com.estoqueApp.estoqueApp.service.CategoriaService;
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
@RequestMapping("/categoria")
@Tag(name = "categoria", description = "Endpoints para gerenciamento de categorias")
public class CategoriaController {

    private final CategoriaService categoriaService;

    @GetMapping
    public ResponseEntity<List<CategoriaDto>> findAll() throws RegraDeNegocioException {
        log.info("Listando categorias");
        List<CategoriaDto> categorias = categoriaService.findAll();
        log.info("Categorias listadas com sucesso!");
        return new ResponseEntity<>(categorias, HttpStatus.OK);
    }

    @GetMapping("/{idCategoria}")
    public ResponseEntity<CategoriaDto> findById(@PathVariable Integer idCategoria) throws RegraDeNegocioException {
        log.info("Buscando categoria por ID: {}", idCategoria);
        CategoriaDto categoria = categoriaService.findById(idCategoria);
        log.info("Categoria encontrada com sucesso!");
        return new ResponseEntity<>(categoria, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CategoriaDto> create(@Valid @RequestBody CategoriaCreateDto categoriaCreateDto) throws RegraDeNegocioException {
        log.info("Criando uma categoria");
        CategoriaDto categoria = categoriaService.create(categoriaCreateDto);
        log.info("Categoria criada com sucesso!");
        return new ResponseEntity<>(categoria, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Integer id) throws RegraDeNegocioException {
        log.info("Deletando uma categoria");
        categoriaService.delete(id);
        log.info("Categoria deletada com sucesso!");
        return ResponseEntity.ok("Categoria removida com sucesso!");
    }
}

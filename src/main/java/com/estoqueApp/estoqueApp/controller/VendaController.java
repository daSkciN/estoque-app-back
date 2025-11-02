package com.estoqueApp.estoqueApp.controller;

import com.estoqueApp.estoqueApp.dto.Venda.VendaCreateDto;
import com.estoqueApp.estoqueApp.dto.Venda.VendaDto;
import com.estoqueApp.estoqueApp.exception.RegraDeNegocioException;
import com.estoqueApp.estoqueApp.repository.VendaRepository;
import com.estoqueApp.estoqueApp.service.VendaService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/venda")
@Tag(name = "Venda", description = "Endpoints para gerenciamento de vendas")
public class VendaController {

    private final VendaService vendaService;

    @GetMapping
    public ResponseEntity<List<VendaDto>> findAll() throws RegraDeNegocioException {
        log.info("Listando vendas");
        List<VendaDto> vendas = vendaService.findAll();
        log.info("Vendas listadas com sucesso!");
        return new ResponseEntity<>(vendas, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<VendaDto> findById(@PathVariable Integer id) throws RegraDeNegocioException {
        log.info("Listando uma venda");
        VendaDto venda = vendaService.findById(id);
        log.info("Venda listada com sucesso!");
        return new ResponseEntity<>(venda, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<VendaDto> create(@RequestBody VendaCreateDto vendaCreateDto) throws RegraDeNegocioException {
        log.info("Criando uma venda");
        try {
            VendaDto venda = vendaService.create(vendaCreateDto);
            log.info("Venda criada com sucesso!");
            return new ResponseEntity<>(venda, HttpStatus.CREATED);
        } catch (Exception e) {
            log.error("Erro ao criar venda: {}", e.getMessage(), e);
            throw e;
        }
    }
}

package com.estoqueApp.estoqueApp.service;

import com.estoqueApp.estoqueApp.dto.EntradaEstoque.EntradaEstoqueCreateDto;
import com.estoqueApp.estoqueApp.dto.EntradaEstoque.EntradaEstoqueDto;
import com.estoqueApp.estoqueApp.dto.Produto.ProdutoDto;
import com.estoqueApp.estoqueApp.entity.EntradaEstoque;
import com.estoqueApp.estoqueApp.entity.Produto;
import com.estoqueApp.estoqueApp.exception.RegraDeNegocioException;
import com.estoqueApp.estoqueApp.repository.EntradaEstoqueRepository;
import com.estoqueApp.estoqueApp.repository.ProdutoRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EntradaEstoqueService {

    private final EntradaEstoqueRepository entradaEstoqueRepository;
    private final ProdutoRepository produtoRepository;
    private final ObjectMapper objectMapper;

    public List<EntradaEstoqueDto> findAll() throws RegraDeNegocioException {
        List<EntradaEstoqueDto> entradaEstoqueDtos = entradaEstoqueRepository.findAll().stream()
                .map(entradaEstoque -> {
                    EntradaEstoqueDto dto = objectMapper.convertValue(entradaEstoque, EntradaEstoqueDto.class);

                    dto.setDataEntrada(entradaEstoque.getData());
                    dto.setIdProduto(entradaEstoque.getProduto().getIdProduto());
                    dto.setNomeProduto(entradaEstoque.getProduto().getNome());

                    return dto;
                })
                .toList();

        if (entradaEstoqueDtos.isEmpty()) {
            throw new RegraDeNegocioException("Nenhuma entrada no estoque encontrada", HttpStatus.NOT_FOUND);
        }

        return entradaEstoqueDtos;
    }

    public EntradaEstoqueDto findById(Integer idEntradaEstoque) throws RegraDeNegocioException{
        EntradaEstoque entradaEstoque = entradaEstoqueRepository.findById(idEntradaEstoque)
                .orElseThrow(() -> new RegraDeNegocioException("Entrada de estoque não encontrada", HttpStatus.NOT_FOUND));

        EntradaEstoqueDto dto = objectMapper.convertValue(entradaEstoque, EntradaEstoqueDto.class);

        dto.setDataEntrada(entradaEstoque.getData());
        dto.setIdProduto(entradaEstoque.getProduto().getIdProduto());
        dto.setNomeProduto(entradaEstoque.getProduto().getNome());

        return dto;
    }
    public EntradaEstoqueDto create(EntradaEstoqueCreateDto entradaEstoqueCreateDto) throws RegraDeNegocioException {
        Produto produto = produtoRepository.findById(entradaEstoqueCreateDto.getIdProduto())
                .orElseThrow(() -> new RegraDeNegocioException("Produto não encontrado", HttpStatus.NOT_FOUND));
        if (entradaEstoqueCreateDto.getQuantidade() <= 0){
            throw new RegraDeNegocioException("A quantidade deve ser maior que zero", HttpStatus.BAD_REQUEST);
        }
        EntradaEstoque entradaEstoque = new EntradaEstoque();
        entradaEstoque.setProduto(produto);
        entradaEstoque.setQuantidade(entradaEstoqueCreateDto.getQuantidade());
        entradaEstoque.setData(LocalDateTime.now());

        entradaEstoque = entradaEstoqueRepository.save(entradaEstoque);

        produto.setQuantidadeEstoque(produto.getQuantidadeEstoque() + entradaEstoque.getQuantidade());
        produtoRepository.save(produto);

        EntradaEstoqueDto entradaEstoqueDto = new EntradaEstoqueDto();

        entradaEstoqueDto.setDataEntrada(entradaEstoque.getData());
        entradaEstoqueDto.setIdEntradaEstoque(entradaEstoque.getIdEntradaEstoque());
        entradaEstoqueDto.setNomeProduto(produto.getNome());
        entradaEstoqueDto.setQuantidade(entradaEstoque.getQuantidade());
        entradaEstoqueDto.setIdProduto(produto.getIdProduto());

        return entradaEstoqueDto;
    }

    public void delete(Integer id) throws RegraDeNegocioException {
        EntradaEstoque entradaEstoque = entradaEstoqueRepository.findById(id).orElseThrow(() -> new RegraDeNegocioException("Entrada de estoque não encontrada", HttpStatus.NOT_FOUND));
        entradaEstoqueRepository.delete(entradaEstoque);
    }
}

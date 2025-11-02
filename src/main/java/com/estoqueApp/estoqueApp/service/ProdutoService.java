package com.estoqueApp.estoqueApp.service;

import com.estoqueApp.estoqueApp.dto.Produto.ProdutoCreateDto;
import com.estoqueApp.estoqueApp.dto.Produto.ProdutoDto;
import com.estoqueApp.estoqueApp.entity.Categoria;
import com.estoqueApp.estoqueApp.entity.Produto;
import com.estoqueApp.estoqueApp.exception.RegraDeNegocioException;
import com.estoqueApp.estoqueApp.repository.CategoriaRepository;
import com.estoqueApp.estoqueApp.repository.ProdutoRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProdutoService {
    private final ProdutoRepository produtoRepository;
    private final CategoriaRepository categoriaRepository;
    private final ObjectMapper objectMapper;

    public List<ProdutoDto> findAll() throws RegraDeNegocioException {
        List<ProdutoDto> produtos = produtoRepository.findAll().stream()
                .map(produto -> {
                    ProdutoDto dto = objectMapper.convertValue(produto, ProdutoDto.class);

                    if (produto.getCategoria() != null) {
                        dto.setNomeCategoria(produto.getCategoria().getNome());
                    }

                    return dto;
                })
                .toList();

        if (produtos.isEmpty()) {
            throw new RegraDeNegocioException("Nenhum produto encontrado", HttpStatus.NOT_FOUND);
        }

        return produtos;
    }

    public ProdutoDto findById(Integer idProduto) throws RegraDeNegocioException{
        Produto produto = produtoRepository.findById(idProduto)
                .orElseThrow(() -> new RegraDeNegocioException("Produto não encontrado", HttpStatus.NOT_FOUND));

        ProdutoDto dto = objectMapper.convertValue(produto, ProdutoDto.class);

        if (produto.getCategoria() != null) {
            dto.setNomeCategoria(produto.getCategoria().getNome());
        }

        return dto;
    }

    public ProdutoDto create(ProdutoCreateDto produtoCreateDto) throws RegraDeNegocioException {
        boolean produtoExiste = produtoRepository.existsByNomeIgnoreCase(produtoCreateDto.getNome());
        if (produtoExiste) {
            throw new RegraDeNegocioException("Produto já existe.", HttpStatus.BAD_REQUEST);
        }

        Categoria categoria = categoriaRepository.findByNome(produtoCreateDto.getNomeCategoria())
                .orElseThrow(() -> new RegraDeNegocioException(
                        "Categoria não encontrada.", HttpStatus.NOT_FOUND));

        Produto produto = new Produto();
        produto.setNome(produtoCreateDto.getNome());
        produto.setPrecoCusto(produtoCreateDto.getPrecoCusto());
        produto.setPrecoVenda(produtoCreateDto.getPrecoVenda());
        produto.setCategoria(categoria);

        produto = produtoRepository.save(produto);

        ProdutoDto produtoDto = new ProdutoDto();
        produtoDto.setIdProduto(produto.getIdProduto());
        produtoDto.setNome(produto.getNome());
        produtoDto.setPrecoCusto(produto.getPrecoCusto());
        produtoDto.setPrecoVenda(produto.getPrecoVenda());
        produtoDto.setNomeCategoria(produto.getCategoria().getNome());

        return produtoDto;
    }

    public ProdutoDto update(Integer idProduto, ProdutoCreateDto produtoCreateDto) throws RegraDeNegocioException {
        Produto produtoExistente = produtoRepository.findById(idProduto)
                .orElseThrow(() -> new RegraDeNegocioException("Produto não encontrado.", HttpStatus.NOT_FOUND));

        boolean outroProdutoComMesmoNome = produtoRepository.existsByNomeIgnoreCaseAndIdProdutoNot(
                produtoCreateDto.getNome(), idProduto);
        if (outroProdutoComMesmoNome) {
            throw new RegraDeNegocioException("Outro produto com esse nome já existe.", HttpStatus.BAD_REQUEST);
        }

        Categoria categoria = categoriaRepository.findByNome(produtoCreateDto.getNomeCategoria())
                .orElseThrow(() -> new RegraDeNegocioException("Categoria não encontrada.", HttpStatus.NOT_FOUND));

        produtoExistente.setNome(produtoCreateDto.getNome());
        produtoExistente.setPrecoCusto(produtoCreateDto.getPrecoCusto());
        produtoExistente.setPrecoVenda(produtoCreateDto.getPrecoVenda());
        produtoExistente.setCategoria(categoria);

        Produto produtoAtualizado = produtoRepository.save(produtoExistente);

        ProdutoDto produtoDto = new ProdutoDto();
        produtoDto.setIdProduto(produtoAtualizado.getIdProduto());
        produtoDto.setNome(produtoAtualizado.getNome());
        produtoDto.setPrecoCusto(produtoAtualizado.getPrecoCusto());
        produtoDto.setPrecoVenda(produtoAtualizado.getPrecoVenda());
        produtoDto.setNomeCategoria(produtoAtualizado.getCategoria().getNome());

        return produtoDto;
    }


    public void delete(Integer id) throws Exception {
        Produto produto = produtoRepository.findById(id)
                .orElseThrow(() -> new Exception("Produto não encontrado!"));
        produtoRepository.delete(produto);
    }
}

package com.estoqueApp.estoqueApp.service;

import com.estoqueApp.estoqueApp.dto.Categoria.CategoriaCreateDto;
import com.estoqueApp.estoqueApp.dto.Categoria.CategoriaDto;
import com.estoqueApp.estoqueApp.dto.Produto.ProdutoDto;
import com.estoqueApp.estoqueApp.entity.Categoria;
import com.estoqueApp.estoqueApp.exception.RegraDeNegocioException;
import com.estoqueApp.estoqueApp.repository.CategoriaRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoriaService {

    private final CategoriaRepository categoriaRepository;
    private final ObjectMapper objectMapper;

    public List<CategoriaDto> findAll() throws RegraDeNegocioException {
        List<CategoriaDto> categorias = categoriaRepository.findAll().stream()
                .map(categoria -> {
                    CategoriaDto dto = objectMapper.convertValue(categoria, CategoriaDto.class);

                    return dto;
                })
                .toList();

        if (categorias.isEmpty()) {
            throw new RegraDeNegocioException("Nenhuma categoria encontrada", HttpStatus.NOT_FOUND);
        }

        return categorias;
    }

    public CategoriaDto findById(Integer idCategoria) throws RegraDeNegocioException {
        Categoria categoria = categoriaRepository.findById(idCategoria).orElseThrow(() -> new RegraDeNegocioException("Categoria não encontrada", HttpStatus.NOT_FOUND));

        return objectMapper.convertValue(categoria, CategoriaDto.class);
    }

    public CategoriaDto create(CategoriaCreateDto categoriaCreateDto) throws RegraDeNegocioException {
        boolean categoriaExiste = categoriaRepository.findByNome(categoriaCreateDto.getNome()).isPresent();
        if (categoriaExiste) {
            throw new RegraDeNegocioException("Categoria já existe.", HttpStatus.BAD_REQUEST);
        }
        Categoria categoria = new Categoria();
        categoria.setNome(categoriaCreateDto.getNome());
        categoria = categoriaRepository.save(categoria);

        return objectMapper.convertValue(categoria, CategoriaDto.class);
    }

    public void delete(Integer id) throws RegraDeNegocioException {
        Categoria categoria = categoriaRepository.findById(id).orElseThrow(() -> new RegraDeNegocioException("Categoria não encontrada", HttpStatus.NOT_FOUND));
        categoriaRepository.delete(categoria);
    }
}

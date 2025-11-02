package com.estoqueApp.estoqueApp.service;

import com.estoqueApp.estoqueApp.dto.ItemVenda.ItemVendaCreateDto;
import com.estoqueApp.estoqueApp.dto.ItemVenda.ItemVendaDto;
import com.estoqueApp.estoqueApp.dto.Venda.VendaCreateDto;
import com.estoqueApp.estoqueApp.dto.Venda.VendaDto;
import com.estoqueApp.estoqueApp.entity.ItemVenda;
import com.estoqueApp.estoqueApp.entity.Produto;
import com.estoqueApp.estoqueApp.entity.Venda;
import com.estoqueApp.estoqueApp.exception.RegraDeNegocioException;
import com.estoqueApp.estoqueApp.repository.ItemVendaRepository;
import com.estoqueApp.estoqueApp.repository.ProdutoRepository;
import com.estoqueApp.estoqueApp.repository.VendaRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class VendaService {

    private final VendaRepository vendaRepository;
    private final ItemVendaRepository itemVendaRepository;
    private final ProdutoRepository produtoRepository;
    private final ObjectMapper objectMapper;

    public VendaDto create(VendaCreateDto vendaCreateDto) throws RegraDeNegocioException {
        Venda venda = new Venda();
        venda.setData(LocalDateTime.now());

        BigDecimal valorTotal = BigDecimal.ZERO;
        List<ItemVenda> itens = new ArrayList<>();

        for (ItemVendaCreateDto itemVendaCreateDto : vendaCreateDto.getItens()) {
            Produto produto = produtoRepository.findById(itemVendaCreateDto.getIdProduto())
                    .orElseThrow(() -> new RegraDeNegocioException("Produto não encontrado", HttpStatus.NOT_FOUND));
            if (itemVendaCreateDto.getQuantidade() == null || itemVendaCreateDto.getQuantidade() <= 0) {
                throw new RegraDeNegocioException("A quantidade de cada item deve ser maior que 0.", HttpStatus.BAD_REQUEST);
            }
            if(produto.getQuantidadeEstoque() < itemVendaCreateDto.getQuantidade()){
                throw new RegraDeNegocioException("Quantidade insuficiente em estoque", HttpStatus.BAD_REQUEST);
            }
            produto.setQuantidadeEstoque(produto.getQuantidadeEstoque() - itemVendaCreateDto.getQuantidade());
            produtoRepository.save(produto);

            ItemVenda itemVenda = new ItemVenda();
            itemVenda.setVenda(venda);
            itemVenda.setProduto(produto);
            itemVenda.setQuantidade(itemVendaCreateDto.getQuantidade());
            itemVenda.setPrecoUnitario(itemVendaCreateDto.getPrecoUnitario());

            itens.add(itemVenda);
            valorTotal = valorTotal.add(itemVenda.getPrecoUnitario().multiply(BigDecimal.valueOf(itemVendaCreateDto.getQuantidade())));
        }
        venda.setValorTotal(valorTotal);
        venda.setItens(itens);

        venda = vendaRepository.save(venda);

        VendaDto vendaDto = new VendaDto();
        vendaDto.setIdVenda(venda.getIdVenda());
        vendaDto.setData(venda.getData());
        vendaDto.setValorTotal(venda.getValorTotal());

        List<ItemVendaDto> itensDto = venda.getItens().stream()
                .map(item -> {
                    ItemVendaDto dto = new ItemVendaDto();
                    dto.setIdItemVenda(item.getIdItemVenda());
                    dto.setIdProduto(item.getProduto().getIdProduto());
                    dto.setNomeProduto(item.getProduto().getNome());
                    dto.setQuantidade(item.getQuantidade());
                    dto.setIdItemVenda(item.getIdItemVenda());
                    dto.setPrecoUnitario(item.getPrecoUnitario());
                    return dto;
                })
                .toList();

        vendaDto.setItens(itensDto);
        return vendaDto;
    }

    public List<VendaDto> findAll() throws RegraDeNegocioException {
        List<Venda> vendas = vendaRepository.findAll();

        if (vendas.isEmpty()) {
            throw new RegraDeNegocioException("Nenhuma venda encontrada", HttpStatus.NOT_FOUND);
        }

        List<VendaDto> vendaDtos = vendas.stream()
                .map(venda -> {
                    VendaDto dto = new VendaDto();
                    dto.setIdVenda(venda.getIdVenda());
                    dto.setData(venda.getData());
                    dto.setValorTotal(venda.getValorTotal());

                    List<ItemVendaDto> itensDto = venda.getItens().stream()
                            .map(item -> {
                                ItemVendaDto itemDto = new ItemVendaDto();
                                itemDto.setIdItemVenda(item.getIdItemVenda());
                                itemDto.setIdProduto(item.getProduto().getIdProduto());
                                itemDto.setNomeProduto(item.getProduto().getNome());
                                itemDto.setQuantidade(item.getQuantidade());
                                return itemDto;
                            })
                            .toList();

                    dto.setItens(itensDto);

                    return dto;
                })
                .toList();

        return vendaDtos;
    }


    public VendaDto findById(Integer idVenda) throws RegraDeNegocioException {
        Venda venda = vendaRepository.findById(idVenda)
                .orElseThrow(() -> new RegraDeNegocioException("Venda não encontrada", HttpStatus.NOT_FOUND));

        VendaDto vendaDto = new VendaDto();
        vendaDto.setIdVenda(venda.getIdVenda());
        vendaDto.setData(venda.getData());
        vendaDto.setValorTotal(venda.getValorTotal());

        List<ItemVendaDto> itensDto = venda.getItens().stream()
                .map(item -> {
                    ItemVendaDto dto = new ItemVendaDto();
                    dto.setIdItemVenda(item.getIdItemVenda());
                    dto.setIdProduto(item.getProduto().getIdProduto());
                    dto.setNomeProduto(item.getProduto().getNome());
                    dto.setQuantidade(item.getQuantidade());
                    return dto;
                })
                .toList();

        vendaDto.setItens(itensDto);
        return vendaDto;
    }

}

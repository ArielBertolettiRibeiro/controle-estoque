package com.controle.estoque.aplication.service;

import com.controle.estoque.config.mapper.ProdutoMapper;
import com.controle.estoque.infrastructure.repository.MovimentacaoEstoqueRepository;
import com.controle.estoque.infrastructure.repository.ProdutoRepository;
import com.controle.estoque.infrastructure.repository.VendaRepository;
import com.controle.estoque.model.domain.entities.MovimentacaoEstoque;
import com.controle.estoque.model.domain.entities.Produto;
import com.controle.estoque.model.domain.entities.TipoMovimentacao;
import com.controle.estoque.model.domain.entities.Venda;
import com.controle.estoque.model.request.ProdutoRequest;
import com.controle.estoque.model.response.ProdutoResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Transactional
@Service
public class ProdutoService {

    private ProdutoMapper mapper;
    private ProdutoRepository repository;
    private MovimentacaoEstoqueRepository movimentacaoEstoqueRepository;
    private VendaRepository vendaRepository;

    public ProdutoService(ProdutoMapper mapper, ProdutoRepository repository, MovimentacaoEstoqueRepository movimentacaoEstoqueRepository, VendaRepository vendaRepository) {
        this.mapper = mapper;
        this.repository = repository;
        this.movimentacaoEstoqueRepository = movimentacaoEstoqueRepository;
        this.vendaRepository = vendaRepository;
    }

    public ProdutoResponse cadastrar(ProdutoRequest request) {
        Produto produto = mapper.toEntity(request);
        Produto produtoSalvo = repository.save(produto);

        return mapper.toResponse(produtoSalvo);
    }

    public List<ProdutoResponse> listarDisponiveis() {
        return repository.findByQuantidadeDisponivelGreaterThan(0)
                .stream()
                .map(mapper::toResponse)
                .collect(Collectors.toList());
    }

    public ProdutoResponse buscarPeloNome(String nome) {
        Produto produto = repository.findByNome(nome);
        return mapper.toResponse(produto);
    }

    public ProdutoResponse atualizar(Long id, ProdutoRequest produtoRequest) {
        Produto produto = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));

        mapper.toEntity(produtoRequest);
        produto.setNome(produtoRequest.getNome());
        produto.setCategoria(produtoRequest.getCategoria());
        produto.setPreco(produtoRequest.getPreco());
        produto.setQuantidadeDisponivel(produtoRequest.getQuantiadeDisponivel());

        Produto atualizado = repository.save(produto);
        return mapper.toResponse(atualizado);
    }

    public ProdutoResponse reporEstoque(String nome, int quantidade) {
        Produto produto = repository.findByNome(nome);
        if (quantidade <= 0) {
            throw new IllegalArgumentException("Quantidade adicional deve ser maior que zero!");
        }

        produto.setQuantidadeDisponivel(produto.getQuantidadeDisponivel() + quantidade);
        repository.save(produto);

        MovimentacaoEstoque movimentacao = new MovimentacaoEstoque(produto, quantidade, TipoMovimentacao.ENTRADA);
        movimentacaoEstoqueRepository.save(movimentacao);

        return mapper.toResponse(produto);
    }

    public ProdutoResponse venderProduto(Long id, int quantidadeVendida) {
        Produto produto = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado!"));

        if (quantidadeVendida <= 0) {
            throw new IllegalArgumentException("A quantidade vendida deve ser maior que zero!");
        }

        if (produto.getQuantidadeDisponivel() < quantidadeVendida) {
            throw new IllegalArgumentException("Estoque insuficiente para venda!");
        }

        produto.setQuantidadeDisponivel(produto.getQuantidadeDisponivel() - quantidadeVendida);
        repository.save(produto);

        Venda venda = new Venda(quantidadeVendida, produto);
        vendaRepository.save(venda);

        MovimentacaoEstoque movimentacaoEstoque = new MovimentacaoEstoque(produto, quantidadeVendida, TipoMovimentacao.SAIDA);
        movimentacaoEstoqueRepository.save(movimentacaoEstoque);

        return mapper.toResponse(produto);
    }

    public void deletar(Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Produto não encontrado!");
        }
     repository.deleteById(id);
    }
}

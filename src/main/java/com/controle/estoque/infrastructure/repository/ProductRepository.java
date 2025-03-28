package com.controle.estoque.infrastructure.repository;

import com.controle.estoque.domain.entities.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    // Busca apenas produtos com quantidade disponível maior que 0
    Page<Product> findByQuantidadeDisponivelGreaterThan(Integer quantidade, Pageable pageable);

    // Busca um produto exato pelo nome
    Optional<Product> findByNomeContainingIgnoreCase(String nome);
}

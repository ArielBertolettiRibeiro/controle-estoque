package com.controle.estoque.application.controllers;

import com.controle.estoque.application.service.ProductService;
import com.controle.estoque.dto.request.ProductRequest;
import com.controle.estoque.dto.response.ProductResponse;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@Transactional
@RestController
@RequestMapping("products")
public class ProductController {

    private final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<ProductResponse> create(@RequestBody @Valid ProductRequest produtoRequest,
                                                  UriComponentsBuilder uriComponentsBuilder) {

        ProductResponse response = service.create(produtoRequest);

        var uri = uriComponentsBuilder.path("/produtos/{id}").buildAndExpand(response.getId()).toUri();

        return ResponseEntity.created(uri).body(response);
    }

    @GetMapping
    public ResponseEntity<Page<ProductResponse>> getAll(@PageableDefault(size = 20, sort = {"nome"}) Pageable pageable) {
        Page<ProductResponse> produtos =  service.getAll(pageable);
        return ResponseEntity.ok(produtos);
    }

    @GetMapping("/find")
    public ResponseEntity<ProductResponse> findByName(@RequestParam String nome) {
        return service.findByName(nome)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}/restock")
    public ResponseEntity<ProductResponse> restock(@PathVariable Long id, @RequestParam int quantidade) {
        ProductResponse response = service.restock(id, quantidade);

        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}/sell")
    public ResponseEntity<ProductResponse> sell(@PathVariable Long id, @RequestParam int quantidade) {
        ProductResponse response = service.sell(id, quantidade);

        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductResponse> update(@PathVariable Long id, @RequestBody ProductRequest produtoRequest){
        ProductResponse produto = service.update(id, produtoRequest);
        return ResponseEntity.ok(produto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);

        return ResponseEntity.noContent().build();
    }
}

package com.example.cacheweb.controller;

import com.example.cacheweb.model.Product;
import com.example.cacheweb.service.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/produtos")
public class ProductController {

    private final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    @GetMapping("/sem-cache/{id}")
    public ResponseEntity<Product> findNoCache(@PathVariable Long id) {
        return service.findByIdNoCache(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/com-cache/{id}")
    public ResponseEntity<Product> findCached(@PathVariable Long id) {
        return service.findByIdCached(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/condicional/{id}")
    public ResponseEntity<Product> findConditional(@PathVariable Long id) {
        return service.findByIdConditional(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/com-cache")
    public Page<Product> findPagedCached(Pageable pageable) {
        return service.findPagedCached(pageable);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> update(@PathVariable Long id, @RequestBody Product product) {
        Optional<Product> existing = service.findByIdNoCache(id);
        if (existing.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        product.setId(id);
        return ResponseEntity.ok(service.updateProduct(product));
    }
}

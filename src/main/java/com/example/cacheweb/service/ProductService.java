package com.example.cacheweb.service;

import com.example.cacheweb.model.Product;
import com.example.cacheweb.repository.ProductRepository;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository repository;

    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    public Optional<Product> findByIdNoCache(Long id) {
        return repository.findById(id);
    }

    @Cacheable("products")
    public Optional<Product> findByIdCached(Long id) {
        return repository.findById(id);
    }

    @CacheEvict(value = "products", key = "#product.id")
    public Product updateProduct(Product product) {
        return repository.save(product);
    }
}

package com.example.cacheweb.service;

import com.example.cacheweb.model.Product;
import com.example.cacheweb.repository.ProductRepository;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository repository;

    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }
    
    public List<Product> findAllNoCache() {
        return repository.findAll();
    }

    public Optional<Product> findByIdNoCache(Long id) {
        return repository.findById(id);
    }

    @Cacheable("products")
    public Optional<Product> findByIdCached(Long id) {
        return repository.findById(id);
    }

    @Cacheable(value = "products", key = "#id", condition = "#id > 1000")
    public Optional<Product> findByIdConditional(Long id) {
        return repository.findById(id);
    }

    @Cacheable(value = "productsPage", key = "'page_' + #pageable.pageNumber + '_' + #pageable.pageSize")
    public Page<Product> findPagedCached(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Cacheable(value = "productsList")
    public List<Product> findAllCached() {
        return repository.findAll();
    }
    
    @CacheEvict(value = "products", key = "#product.id")
    public Product updateProduct(Product product) {
        return repository.save(product);
    }
}

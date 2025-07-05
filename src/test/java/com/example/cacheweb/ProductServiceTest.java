package com.example.cacheweb;

import com.example.cacheweb.model.Product;
import com.example.cacheweb.repository.ProductRepository;
import com.example.cacheweb.service.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@Import(ProductService.class)
class ProductServiceTest {

    @Autowired
    private ProductService service;
    @Autowired
    private ProductRepository repository;

    @Test
    void updateProductEvictsCache() {
        Product p = repository.save(new Product("Teste", BigDecimal.ONE));
        p.setName("Atualizado");
        Product updated = service.updateProduct(p);
        assertThat(updated.getName()).isEqualTo("Atualizado");
    }
}

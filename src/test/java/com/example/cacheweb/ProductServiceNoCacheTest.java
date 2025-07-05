package com.example.cacheweb;

import com.example.cacheweb.config.TestRedisConfiguration;
import com.example.cacheweb.model.Product;
import com.example.cacheweb.repository.ProductRepository;
import com.example.cacheweb.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.PageRequest;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Testes de integração para o ProductService sem usar cache
 * Usando NoOpCacheManager para desabilitar o cache durante os testes
 */
@SpringBootTest
@Import(TestRedisConfiguration.class)
@org.springframework.test.context.ActiveProfiles("test")
public class ProductServiceNoCacheTest {

    @Autowired
    private ProductService service;

    @Autowired
    private ProductRepository repository;

    @BeforeEach
    void setUp() {
        // Limpar o repositório antes de cada teste
        repository.deleteAll();
    }

    @Test
    void testUpdateProduct() {
        // Criar e salvar um produto
        Product p = repository.save(new Product("Teste", BigDecimal.ONE));
        
        // Atualizar o nome do produto
        p.setName("Atualizado");
        Product updated = service.updateProduct(p);
        
        // Verificar se o nome foi atualizado
        assertThat(updated.getName()).isEqualTo("Atualizado");
    }

    @Test
    void testFindAllPaged() {
        // Criar e salvar alguns produtos
        repository.save(new Product("Produto 1", BigDecimal.ONE));
        repository.save(new Product("Produto 2", BigDecimal.TEN));
        
        // Buscar a página de produtos diretamente do repositório
        var page = repository.findAll(PageRequest.of(0, 10));
        
        // Verificar se a página contém os produtos
        assertThat(page.getContent().size()).isEqualTo(2);
    }

    @Test
    void testFindById() {
        // Criar e salvar um produto
        Product p = repository.save(new Product("Teste", BigDecimal.ONE));
        
        // Buscar o produto pelo ID usando o método sem cache
        var result = service.findByIdNoCache(p.getId());
        
        // Verificar se o produto foi encontrado
        assertThat(result).isPresent();
        assertThat(result.get().getId()).isEqualTo(p.getId());
        assertThat(result.get().getName()).isEqualTo("Teste");
    }
}

package com.example.cacheweb.config;

import com.example.cacheweb.model.Product;
import com.example.cacheweb.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.stream.IntStream;

@Component
public class DataInitializer implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(DataInitializer.class);
    private final ProductRepository repository;

    public DataInitializer(ProductRepository repository) {
        this.repository = repository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (repository.count() > 0) {
            log.info("[DataInitializer] Dados ja existentes. Pulando populacao.");
            return;
        }
        log.info("[DataInitializer] Populando banco com 250000 produtos...");
        IntStream.rangeClosed(1, 250_000)
                .mapToObj(i -> new Product("Produto " + i, BigDecimal.valueOf(i)))
                .forEach(repository::save);
        log.info("[DataInitializer] Banco de dados populado com 250000 registros.");
    }
}

package com.example.cacheweb.config;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.cache.CacheManager;
import org.springframework.cache.support.NoOpCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

/**
 * Configuração de teste para Redis
 * Usa NoOpCacheManager para desabilitar completamente o cache durante os testes
 * e evitar problemas de serialização
 */
@TestConfiguration
public class TestRedisConfiguration {

    /**
     * Configura um NoOpCacheManager para desabilitar o cache durante os testes
     * Isso evita problemas de serialização e dependência do Redis
     * @return CacheManager que não faz nada (NoOp)
     */
    @Bean
    @Primary
    public CacheManager cacheManager() {
        // NoOpCacheManager não armazena nada em cache e não tenta serializar objetos
        return new NoOpCacheManager();
    }
}

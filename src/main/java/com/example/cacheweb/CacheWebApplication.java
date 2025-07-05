package com.example.cacheweb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class CacheWebApplication {
    public static void main(String[] args) {
        SpringApplication.run(CacheWebApplication.class, args);
    }
}

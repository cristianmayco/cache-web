# Descrição do Projeto: Cache-Web com Spring Boot e Redis

## 1. Visão Geral

Este projeto é uma aplicação de exemplo desenvolvida para demonstrar o uso e os benefícios de uma camada de cache distribuído (Redis) em uma aplicação web moderna construída com Java 21 e Spring Boot. A aplicação se conecta a um banco de dados PostgreSQL, que é populado com um volume significativo de dados (250.000 registros) para simular um cenário realista onde a performance de consultas ao banco é um fator crítico.

O objetivo principal é ilustrar como o Spring Cache Abstraction simplifica a implementação de caching, reduzindo a latência, diminuindo a carga sobre o banco de dados e melhorando a escalabilidade da aplicação.

## 2. Arquitetura e Tecnologias

- **Linguagem:** Java 21
- **Framework:** Spring Boot 3.x
- **Banco de Dados:** PostgreSQL
- **Cache:** Redis
- **Containerização:** Docker e Docker Compose

O projeto é totalmente containerizado usando Docker. O `docker-compose.yml` orquestra os serviços de PostgreSQL e Redis, garantindo um ambiente de desenvolvimento e teste consistente e fácil de configurar.

## 3. Funcionalidades Principais

- **API REST:** Endpoints para consultar dados de uma entidade principal.
- **Comparativo de Performance:** Existem endpoints equivalentes com e sem cache para permitir uma comparação direta do tempo de resposta.
- **População de Dados:** Um script automatizado que popula a tabela principal com 250.000 registros de exemplo na inicialização da aplicação.
- **Estratégias de Cache:** Demonstração do uso de anotações como `@Cacheable`, `@CacheEvict` e `@CachePut`. Consulte `docs/key_generator.md` para exemplos avançados de `KeyGenerator`.
- **Consulta Paginada com Cache:** Endpoint `GET /produtos/com-cache` aceita parâmetros de paginação e armazena as páginas em cache.
- **Limpeza de Cache:** Endpoint `DELETE /cache/clear-all` permite esvaziar todos os caches.
- **Cache Condicional:** Uso de `@Cacheable(condition = "...")` para cenários onde o cache só deve ser ativado sob determinadas condições.
- **Testes Abrangentes:** O projeto incluirá testes de unidade e integração para validar o comportamento do cache (ex: hits, misses, evictions) e garantir a corretude da lógica de negócio.

## 4. Como Executar o Projeto

1.  **Pré-requisitos:**
    -   Docker e Docker Compose instalados.
    -   Java 21 (JDK) e Maven configurados.

2.  **Iniciar a Infraestrutura (Banco de Dados e Cache):**
    ```bash
    docker-compose up -d
    ```
    Este comando irá iniciar os containers do PostgreSQL e do Redis em background.

3.  **Executar a Aplicação Spring Boot:**
    ```bash
    ./mvnw spring-boot:run
    ```
    Na primeira execução, aguarde o log `[DataInitializer]... Banco de dados populado com 250000 registros.` indicar que a população de dados foi concluída.

4.  **Testar os Endpoints:**
    Use uma ferramenta como `curl` ou Postman para testar os endpoints e comparar os tempos de resposta.

5.  **Executar os Testes:**
    Para rodar a suíte de testes e verificar a integridade da aplicação, execute:
    ```bash
    ./mvnw test
    ```

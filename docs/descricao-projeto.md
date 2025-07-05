# Cache Web - Sistema de Demonstração de Cache com Spring Boot

## Visão Geral

O Cache Web é um projeto de demonstração que implementa um sistema de cache utilizando Spring Boot, Redis e PostgreSQL. O projeto foi desenvolvido para demonstrar as melhores práticas de implementação de cache em aplicações Spring Boot, com foco em performance e escalabilidade.

## Tecnologias Utilizadas

- **Java 21**: Linguagem de programação principal
- **Spring Boot 3.x**: Framework para desenvolvimento de aplicações Java
- **Spring Data JPA**: Para persistência de dados
- **Spring Cache**: Para implementação de cache
- **Redis**: Como provedor de cache distribuído
- **PostgreSQL**: Banco de dados relacional
- **Docker**: Para containerização da aplicação
- **Docker Compose**: Para orquestração dos containers

## Arquitetura

O projeto segue uma arquitetura em camadas:

1. **Controller**: Responsável por receber as requisições HTTP e direcioná-las para os serviços apropriados
2. **Service**: Contém a lógica de negócio e gerencia o cache
3. **Repository**: Responsável pela persistência dos dados
4. **Model**: Contém as entidades do domínio

## Funcionalidades

- **Cache de Produtos**: Implementação de cache para produtos, com diferentes estratégias:
  - Cache simples por ID
  - Cache condicional
  - Cache de páginas de resultados
  - Cache de lista completa de produtos
- **Gerenciamento de Cache**: Endpoints para limpar o cache
- **Demonstração de Performance**: Endpoints com e sem cache para comparação de performance
- **Endpoints de Listagem**: Múltiplos endpoints para listar produtos:
  - Listagem completa sem cache
  - Listagem completa com cache
  - Listagem paginada com cache

## Configuração de Ambiente

O projeto utiliza Docker e Docker Compose para facilitar a configuração do ambiente de desenvolvimento. Os seguintes serviços são configurados:

- **app**: Aplicação Spring Boot
- **postgres**: Banco de dados PostgreSQL
- **redis**: Servidor Redis para cache
- **tests**: Container para execução de testes

## Estratégias de Cache Implementadas

1. **Cache Simples**: Utilizando a anotação `@Cacheable` para armazenar resultados de consultas
2. **Cache Condicional**: Utilizando a condição `condition` para determinar quando um resultado deve ser armazenado em cache
3. **Cache de Páginas**: Implementação de cache para resultados paginados com chaves dinâmicas baseadas nos parâmetros de paginação
4. **Cache de Lista Completa**: Cache para armazenar a lista completa de produtos, otimizando consultas frequentes

## Configuração de Testes

O projeto inclui configurações específicas para testes, utilizando perfis do Spring para desabilitar o cache durante os testes e evitar problemas de serialização.

## Documentação

A documentação do projeto está organizada na pasta `docs` e inclui:

1. **Descrição do Projeto**: Este documento
2. **Lista de Tarefas**: Tarefas pendentes e concluídas
3. **Lista de Issues**: Problemas conhecidos e suas soluções
4. **Solução de Cache**: Detalhes sobre a implementação da solução de cache
5. **Manual do Usuário**: Guia simplificado para usuários não técnicos

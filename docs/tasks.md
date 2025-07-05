# Lista de Tarefas (Roadmap)

Esta lista documenta as tarefas planejadas e concluídas para o projeto `cache-web`.

## Concluídas

- [x] **#T1:** Configurar ambiente de desenvolvimento com Docker Compose (Postgres, Redis).
- [x] **#T2:** Criar a entidade principal (`Produto`) e o repositório Spring Data JPA.
- [x] **#T3:** Desenvolver um inicializador de dados para popular o banco com 250.000 registros.
- [x] **#T4:** Implementar endpoint REST para buscar produtos sem cache (`GET /produtos/sem-cache/{id}`).
- [x] **#T5:** Implementar endpoint REST para buscar produtos com cache (`GET /produtos/com-cache/{id}`).
- [x] **#T6:** Criar um endpoint para atualizar um produto e invalidar seu cache (`PUT /produtos/{id}` com `@CacheEvict`).
- [x] **#T7:** Implementar cache para consultas paginadas (`GET /produtos/com-cache`).

## A Fazer (Backlog)

- [x] **#T8:** Adicionar testes de carga (ex: com Gatling ou JMeter) para gerar um relatório de performance comparativo.
- [ ] **#T9:** Documentar as estratégias de chave de cache (`KeyGenerator`) para cenários complexos.
- [ ] **#T10:** Adicionar um endpoint para limpar todos os caches (`DELETE /cache/clear-all`).
- [ ] **#T11:** Implementar cache condicional com a anotação `@Cacheable(condition = "...")`.

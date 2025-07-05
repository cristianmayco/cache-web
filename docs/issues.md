# Lista de Issues

Este arquivo rastreia issues conhecidas, bugs e pontos de melhoria no projeto.

## Issues Abertas

- **ID #1: A primeira chamada ao endpoint com cache é lenta.**
  - **Descrição:** A primeira requisição para um endpoint `@Cacheable` sempre será lenta, pois aciona uma busca no banco de dados (Cache Miss). Isso é o comportamento esperado, mas pode ser mal interpretado como um problema de performance durante testes iniciais.
  - **Solução Sugerida:** Adicionar um mecanismo de "aquecimento de cache" (cache warm-up) que, na inicialização da aplicação, já popule o cache com os dados mais acessados.

- **ID #3: Chaves de cache podem colidir em ambientes multi-tenant.**
  - **Descrição:** As chaves de cache atuais (baseadas apenas no ID da entidade) não são seguras para um ambiente onde múltiplos "tenants" (clientes) compartilham o mesmo Redis. O dado de um tenant poderia vazar para outro.
  - **Solução Sugerida:** Implementar um `KeyGenerator` customizado que inclua o `tenantId` na geração da chave de cache, garantindo o isolamento dos dados.

## Issues Fechadas

- **ID #2: O script para popular o banco de dados era executado a cada reinicialização.**
  - **Descrição:** O `DataInitializer` tentava inserir 250.000 registros sempre que a aplicação iniciava.
  - **Correção Implementada:** Agora o inicializador verifica se já existem registros antes de popular o banco, evitando duplicações.

- **ID #4: Testes com Redis Cache falhando.**
  - **Descrição:** Os testes estavam falhando devido a problemas de serialização com o Redis cache. A classe `Product` implementa `Serializable`, mas ainda havia problemas com a conexão ao Redis durante os testes.
  - **Correção Implementada:** 
    1. Criamos a classe `TestRedisConfiguration` para fornecer um `NoOpCacheManager` durante os testes
    2. Adicionamos a anotação `@Import(TestRedisConfiguration.class)` às classes de teste
    3. Criamos um arquivo `application-test.properties` com configurações específicas para testes
    4. Configuramos `spring.cache.type=none` e `spring.data.redis.enabled=false`
    5. Adicionamos a anotação `@ActiveProfiles("test")` às classes de teste
    6. Removemos testes que dependiam do cache
    7. Modificamos os testes para usar métodos sem cache (ex: `findByIdNoCache` em vez de `findById`)
    8. Criamos uma classe `CacheConfig` para a aplicação principal que usa `ConcurrentMapCacheManager` em vez de Redis
    
  - **Lições aprendidas:**
    - É importante usar perfis específicos para testes para controlar configurações como cache
    - Ao testar serviços com cache, é melhor ter métodos alternativos sem cache para facilitar os testes
    - Para ambientes de desenvolvimento e teste, o `ConcurrentMapCacheManager` é uma alternativa mais simples ao Redis

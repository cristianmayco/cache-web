# Lista de Issues

Este arquivo rastreia issues conhecidas, bugs e pontos de melhoria no projeto.

## Issues Abertas

- **ID #1: A primeira chamada ao endpoint com cache é lenta.**
  - **Descrição:** A primeira requisição para um endpoint `@Cacheable` sempre será lenta, pois aciona uma busca no banco de dados (Cache Miss). Isso é o comportamento esperado, mas pode ser mal interpretado como um problema de performance durante testes iniciais.
  - **Solução Sugerida:** Adicionar um mecanismo de "aquecimento de cache" (cache warm-up) que, na inicialização da aplicação, já popule o cache com os dados mais acessados.

- **ID #2: O script para popular o banco de dados é executado a cada reinicialização.**
  - **Descrição:** Atualmente, o `DataInitializer` tenta inserir 250.000 registros toda vez que a aplicação inicia, o que é ineficiente e causa erros se os dados já existirem. 
  - **Solução Sugerida:** Modificar o `DataInitializer` para verificar se a tabela principal já contém registros antes de iniciar o processo de população.

- **ID #3: Chaves de cache podem colidir em ambientes multi-tenant.**
  - **Descrição:** As chaves de cache atuais (baseadas apenas no ID da entidade) não são seguras para um ambiente onde múltiplos "tenants" (clientes) compartilham o mesmo Redis. O dado de um tenant poderia vazar para outro.
  - **Solução Sugerida:** Implementar um `KeyGenerator` customizado que inclua o `tenantId` na geração da chave de cache, garantindo o isolamento dos dados.

## Issues Fechadas

*(Nenhuma issue fechada ainda.)*

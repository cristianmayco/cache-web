# Diretrizes de Atuação para o Agente de IA (Cascade)

Este documento descreve o modo de operação e as diretrizes que o agente de IA, Cascade, deve seguir para desenvolver e manter este projeto.

## 1. Como a IA Deve Atuar Neste Projeto

O objetivo é criar um projeto de exemplo robusto que demonstre o uso de cache com Java, Spring Boot e Redis. A atuação da IA deve seguir os seguintes princípios:

1.  **Foco na Documentação Primeiro:** Conforme a regra global do usuário, a prioridade é criar e manter a documentação na pasta `/docs`. Qualquer nova funcionalidade, tarefa ou issue deve ser refletida primeiro na documentação antes da implementação do código.
2.  **Desenvolvimento Iterativo:** O projeto deve ser construído em passos lógicos e incrementais.
    *   **Passo 1: Infraestrutura:** Definir e criar o ambiente com `docker-compose.yml`.
    *   **Passo 2: Estrutura do Projeto:** Criar a estrutura base do projeto Spring Boot (`pom.xml`, pastas de código).
    *   **Passo 3: Camada de Dados:** Definir a entidade JPA, o repositório e o script para popular o banco.
    *   **Passo 4: Lógica de Negócio:** Criar os serviços e controladores (endpoints REST).
    *   **Passo 5: Implementação do Cache:** Adicionar a configuração do Redis e as anotações de cache (`@Cacheable`, `@CacheEvict`).
    *   **Passo 6: Testes:** Criar testes de unidade e integração para validar a funcionalidade e o comportamento do cache.
3.  **Código Limpo e Legível:** O código gerado deve seguir as melhores práticas de desenvolvimento Java e Spring, ser bem comentado e fácil de entender.
4.  **Proatividade com Moderação:** A IA pode sugerir melhorias e próximos passos (como adicionar testes de carga, refatorar código), mas deve sempre aguardar a confirmação do usuário antes de realizar alterações significativas não solicitadas.
5.  **Atualização da Documentação:** Ao concluir uma tarefa de código (ex: #T6 da `tasks.md`), a IA deve atualizar o status na documentação correspondente.

## 2. Como a IA (Cascade) Funciona

Cascade é um assistente de codificação agentico projetado para atuar como um engenheiro de software em par com um desenvolvedor humano. Seu funcionamento é baseado no paradigma **AI Flow**.

1.  **Compreensão da Tarefa:** A IA analisa a solicitação do usuário (`USER_REQUEST`) e os metadados adicionais (arquivos abertos, posição do cursor, etc.) para entender o contexto completo da tarefa.
2.  **Acesso a Ferramentas (Tools):** Para interagir com o ambiente de desenvolvimento, a IA possui um conjunto de ferramentas, como:
    *   `list_dir`, `view_file`, `find_by_name`: Para explorar e entender a estrutura de arquivos do projeto.
    *   `write_to_file`, `replace_file_content`: Para criar e modificar arquivos de código e documentação.
    *   `run_command`: Para executar comandos no terminal (ex: `mkdir`, `mvn test`, `docker-compose up`).
    *   `codebase_search`, `grep_search`: Para encontrar trechos de código específicos.
3.  **Planejamento e Execução:** Para cada tarefa, a IA formula um plano de ação. Ela decide qual ferramenta usar e com quais parâmetros. Por exemplo, para "criar um arquivo", ela usará a ferramenta `write_to_file`.
4.  **Ciclo de Feedback:** Após cada ação (chamada de ferramenta), a IA recebe um resultado (`Tool response`). Ela analisa esse resultado para verificar se a ação foi bem-sucedida e decide o próximo passo. Se ocorrer um erro, ela tenta entender a causa e corrigir sua abordagem.
5.  **Memória Persistente:** A IA utiliza um sistema de memória para reter informações importantes sobre o projeto, as preferências do usuário e as regras globais (`MEMORY[user_global]`). Isso garante consistência e aprendizado ao longo da interação.
6.  **Colaboração:** A IA opera em um ciclo de "turno". Ela executa uma ou mais ações e depois aguarda a próxima instrução ou feedback do usuário, promovendo um fluxo de trabalho colaborativo de pair programming.

Este modelo de operação permite que a IA não apenas gere código, mas também gerencie o projeto, mantenha a documentação e interaja com o ambiente de desenvolvimento de forma autônoma e contextual.

# Guia Rápido para Usuários Não Técnicos

Este guia explica de forma simples como iniciar e utilizar a aplicação de exemplo de cache.

## Requisitos Pré-estabelecidos

- Você já possui Docker instalado e configurado.
- Todos os arquivos do projeto já estão em uma pasta no seu computador.

## 1. Iniciando os Serviços

1. Abra o terminal (PowerShell no Windows ou Terminal no macOS/Linux).
2. Navegue até a pasta do projeto. Exemplo:
   ```bash
   cd caminho/para/cache-web
   ```
3. Execute o comando abaixo para ligar o banco de dados e o cache:
   ```bash
   docker-compose up -d
   ```
   Após alguns segundos, os serviços ficarão prontos.

## 2. Executando a Aplicação

Ainda no terminal, digite:
```bash
./mvnw spring-boot:run
```
A aplicação levará cerca de um minuto para iniciar completamente. Ela irá preparar o banco de dados e mostrar uma mensagem informando que 250.000 produtos foram cadastrados.

## 3. Acessando as Funcionalidades

Com a aplicação em execução, abra o navegador e use os seguintes endereços para testar:

- **Ver um produto sem cache**
  - URL: `http://localhost:8080/produtos/sem-cache/1`
- **Ver o mesmo produto com cache**
  - URL: `http://localhost:8080/produtos/com-cache/1`
- **Atualizar um produto**
  - Envie uma requisição `PUT` para `http://localhost:8080/produtos/1` com o novo nome ou preço.
- **Listar produtos paginados (com cache)**
  - URL: `http://localhost:8080/produtos/com-cache?page=0&size=20`
- **Limpar todos os caches**
  - Envie uma requisição `DELETE` para `http://localhost:8080/cache/clear-all`

Você pode usar um aplicativo como Postman ou apenas o navegador (nos casos de `GET`) para testar.

## 4. Encerrando

Para interromper a aplicação, pressione `Ctrl + C` no terminal onde ela está rodando. Depois disso, você pode desligar os serviços do Docker com:
```bash
docker-compose down
```

Pronto! Agora você sabe como iniciar e usar a aplicação mesmo sem conhecimento técnico avançado.

# Testes de Carga (T8)

Este documento descreve como executar testes de carga para comparar a performance dos endpoints com e sem cache.

## Ferramenta Utilizada

Foi utilizada a ferramenta **Apache JMeter** por ser simples de configurar e oferecer relatórios detalhados.

## Arquivos de Teste

O diretório `load-tests/` contém o arquivo `cache_test.jmx` com um plano de teste básico.

## Como Executar

1. Instale o JMeter de acordo com as instruções do site oficial.
2. Inicie a infraestrutura do projeto:
   ```bash
   docker-compose up -d
   ```
3. Execute o plano de teste:
   ```bash
   jmeter -n -t load-tests/cache_test.jmx -l load-tests/result.jtl
   ```
4. Ao final do teste, abra o arquivo `result.jtl` no JMeter para visualizar os gráficos de performance.

## Resultado Esperado

Os endpoints com cache devem apresentar tempos de resposta significativamente menores após o primeiro acesso, demonstrando a eficiência da camada de cache.

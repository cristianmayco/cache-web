# Estratégias de KeyGenerator

Este documento explica como personalizar a geração de chaves de cache para cenários mais complexos.

## Motivação

Em ambientes multi-tenant ou onde a chave de cache depende de múltiplos valores, o `KeyGenerator` padrão pode gerar colisões ou não refletir corretamente o contexto da aplicação.

## Exemplo

```java
@Component("tenantKeyGenerator")
public class TenantKeyGenerator implements KeyGenerator {
    @Override
    public Object generate(Object target, Method method, Object... params) {
        String tenantId = TenantContext.getCurrentTenant();
        return tenantId + "_" + Arrays.toString(params);
    }
}
```

Utilize o gerador com a anotação `@Cacheable`:

```java
@Cacheable(value = "products", keyGenerator = "tenantKeyGenerator")
public Optional<Product> findByIdTenantAware(Long id) {
    return repository.findById(id);
}
```

Este esquema garante que as chaves de cache sejam únicas por `tenant`.

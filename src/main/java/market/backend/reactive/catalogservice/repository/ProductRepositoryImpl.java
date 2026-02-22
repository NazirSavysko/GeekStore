package market.backend.reactive.catalogservice.repository;

import market.backend.reactive.catalogservice.model.Product;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class ProductRepositoryImpl implements ProductRepository {

    private static final ConcurrentHashMap<UUID, Product> productStore = new ConcurrentHashMap<>();


    static {
        UUID id1 = UUID.randomUUID();
        UUID id2 = UUID.randomUUID();
        UUID id3 = UUID.randomUUID();
        productStore.put(id1, new Product(id1,"Product 1", "Description for Product 1", new BigDecimal("19.99"), 100));
        productStore.put(id2, new Product(id2,"Product 2", "Description for Product 2", new BigDecimal("29.99"), 200));
        productStore.put(id3, new Product(id3,"Product 3", "Description for Product 3", new BigDecimal("39.99"), 300));

        System.out.println(productStore);
    }


    @Override
    public Mono<Product> save(final Product product) {
        return Mono.fromCallable(() -> {
            product.setId(UUID.randomUUID());
            productStore.put(product.getId(), product);

            return product;
        });
    }

    @Override
    public Mono<Integer> deleteById(final String id) {
        return Mono.fromCallable(() -> {
            UUID uuid = UUID.fromString(id);

            return productStore.remove(uuid) != null ? 1 : 0;
        });
    }

    @Override
    public Flux<Product> findAll() {
        return Flux.fromIterable(productStore.values());
    }

    @Override
    public Mono<Product> findById(final String id) {
        return Mono.fromCallable(() -> {
            UUID uuid = UUID.fromString(id);

            return productStore.get(uuid);
        });
    }
}

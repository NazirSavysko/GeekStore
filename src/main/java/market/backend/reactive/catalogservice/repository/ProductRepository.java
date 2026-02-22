package market.backend.reactive.catalogservice.repository;

import market.backend.reactive.catalogservice.model.Product;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ProductRepository {

    Mono<Product> save(Product product);

    Mono<Integer> deleteById(String id);

    Flux<Product> findAll();

    Mono<Product> findById(String id);
}

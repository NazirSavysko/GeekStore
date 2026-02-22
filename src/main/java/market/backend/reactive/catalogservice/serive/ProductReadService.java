package market.backend.reactive.catalogservice.serive;

import market.backend.reactive.catalogservice.model.Product;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ProductReadService {
    Mono<Product> getProductById(String id);

    Flux<Product> getAllProducts();
}

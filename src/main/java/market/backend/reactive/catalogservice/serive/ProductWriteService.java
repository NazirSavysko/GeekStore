package market.backend.reactive.catalogservice.serive;

import market.backend.reactive.catalogservice.model.Product;
import reactor.core.publisher.Mono;

public interface ProductWriteService {
    Mono<Product> saveProduct(Product product);

    Mono<Void> deleteProductById(String id);
}

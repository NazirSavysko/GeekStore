package market.backend.reactive.catalogservice.serive.impl;

import lombok.RequiredArgsConstructor;
import market.backend.reactive.catalogservice.exception.ProductNotFoundException;
import market.backend.reactive.catalogservice.model.Product;
import market.backend.reactive.catalogservice.repository.ProductRepository;
import market.backend.reactive.catalogservice.serive.ProductWriteService;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public final class ProductWriteServiceImpl implements ProductWriteService {

    private final ProductRepository productRepository;

    @Override
    public Mono<Product> saveProduct(final Product product) {
        return productRepository.save(product);
    }

    @Override
    public Mono<Void> deleteProductById(final String id) {
        return productRepository.deleteById(id)
                .flatMap(deletedCount -> {
                    if (deletedCount > 0) {
                        return Mono.empty();
                    } else {
                        return Mono.error(new ProductNotFoundException("Product not found for deletion"));
                    }
                });
    }
}

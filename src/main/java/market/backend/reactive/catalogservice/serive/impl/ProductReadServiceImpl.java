package market.backend.reactive.catalogservice.serive.impl;

import lombok.RequiredArgsConstructor;
import market.backend.reactive.catalogservice.exception.ProductNotFoundException;
import market.backend.reactive.catalogservice.model.Product;
import market.backend.reactive.catalogservice.repository.ProductRepository;
import market.backend.reactive.catalogservice.serive.ProductReadService;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public final class ProductReadServiceImpl implements ProductReadService {

    private final ProductRepository productRepository;

    @Override
    public Mono<Product> getProductById(final String id) {
        return productRepository.findById(id)
                .switchIfEmpty(Mono.error(new ProductNotFoundException("Product not found")));
    }

    @Override
    public Flux<Product> getAllProducts() {
        return productRepository.findAll();
    }
}

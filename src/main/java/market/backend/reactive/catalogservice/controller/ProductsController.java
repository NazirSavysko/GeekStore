package market.backend.reactive.catalogservice.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import market.backend.reactive.catalogservice.dto.CreateProductDTO;
import market.backend.reactive.catalogservice.dto.GetProductDTO;
import market.backend.reactive.catalogservice.model.Product;
import market.backend.reactive.catalogservice.serive.ProductReadService;
import market.backend.reactive.catalogservice.serive.ProductWriteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.springframework.http.ResponseEntity.created;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/products")
public final class ProductsController {

    private final ProductWriteService productWriteService;
    private final ProductReadService productReadService;

    @PostMapping
    public Mono<ResponseEntity<GetProductDTO>> createProduct(final @Valid @RequestBody Mono<CreateProductDTO> product,
                                                             final UriComponentsBuilder uriComponentsBuilder) {
        return product
                .map(productDto -> Product.builder()
                        .name(productDto.name())
                        .description(productDto.description())
                        .price(productDto.price())
                        .stockQuantity(productDto.stockQuantity())
                        .build())
                .flatMap(productWriteService::saveProduct)
                .map(product1 -> new GetProductDTO(
                        product1.getId(),
                        product1.getName(),
                        product1.getDescription(),
                        product1.getPrice(),
                        product1.getStockQuantity())
                )
                .map(createdProduct -> created(uriComponentsBuilder
                                .replacePath("/api/v1/products/{id}")
                                .build(createdProduct.id())
                        ).body(createdProduct)
                );
    }

    @GetMapping
    public Flux<?> getAllProducts() {
        return productReadService
                .getAllProducts()
                .map(product -> new GetProductDTO(
                        product.getId(),
                        product.getName(),
                        product.getDescription(),
                        product.getPrice(),
                        product.getStockQuantity())
                );
    }
}

package market.backend.reactive.catalogservice.controller;

import lombok.RequiredArgsConstructor;
import market.backend.reactive.catalogservice.dto.GetProductDTO;
import market.backend.reactive.catalogservice.serive.ProductReadService;
import market.backend.reactive.catalogservice.serive.ProductWriteService;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/products/{postId}")
public final class PostController {

    private final ProductReadService productReadService;
    private final ProductWriteService productWriteService;

    @GetMapping
    public Mono<GetProductDTO> getPostById(@PathVariable final String postId) {
        return this.productReadService
                .getProductById(postId)
                .map(product -> new GetProductDTO(
                        product.getId(),
                        product.getName(),
                        product.getDescription(),
                        product.getPrice())
                );
    }

    @DeleteMapping("/delete")
    public Mono<Void> deletePostById(@PathVariable final String postId) {
        return this.productWriteService.deleteProductById(postId);
    }


}

package market.backend.reactive.catalogservice.dto;

import java.math.BigDecimal;
import java.util.UUID;

public record GetProductDTO(
        UUID id,
        String name,
        String description,
        BigDecimal stockQuantity
) {
}

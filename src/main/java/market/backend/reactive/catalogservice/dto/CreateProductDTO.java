package market.backend.reactive.catalogservice.dto;

import market.backend.reactive.catalogservice.annotation.ValidDescription;
import market.backend.reactive.catalogservice.annotation.ValidName;
import market.backend.reactive.catalogservice.annotation.ValidPrice;

import java.math.BigDecimal;

public record CreateProductDTO(
        @ValidName
        String name,
        @ValidDescription
        String description,
        @ValidPrice
        BigDecimal price
) {
}

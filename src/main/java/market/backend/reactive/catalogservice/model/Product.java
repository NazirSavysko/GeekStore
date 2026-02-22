package market.backend.reactive.catalogservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@AllArgsConstructor
@Builder
public class Product {

    private UUID id;

    private String name;

    private String description;

    private BigDecimal price;

    private Integer stockQuantity;
}

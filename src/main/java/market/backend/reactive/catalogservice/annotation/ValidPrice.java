package market.backend.reactive.catalogservice.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import jakarta.validation.ReportAsSingleViolation;
import jakarta.validation.constraints.NotNull;
import market.backend.reactive.catalogservice.validator.ValidPriceValidator;

import java.lang.annotation.*;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@NotNull(message = "Price cannot be null")
@Constraint(validatedBy = ValidPriceValidator.class)
@Documented
@ReportAsSingleViolation
public @interface ValidPrice {

    String message() default "Invalid price";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
package market.backend.reactive.catalogservice.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import market.backend.reactive.catalogservice.annotation.ValidPrice;

import java.math.BigDecimal;

public final class ValidPriceValidator implements ConstraintValidator<ValidPrice, BigDecimal> {

    @Override
    public boolean isValid(BigDecimal value, ConstraintValidatorContext context) {

        if (value == null) {
            return true;
        }

        if (value.compareTo(BigDecimal.ZERO) < 0) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("Price cannot be negative").addConstraintViolation();

            return false;
        }

        if (value.scale() > 2) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("Price must have maximum 2 decimal places").addConstraintViolation();

            return false;
        }

        return true;
    }
}
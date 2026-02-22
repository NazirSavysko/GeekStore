package market.backend.reactive.catalogservice.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE})
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = {})
@Size(min = 3, max = 100, message = "product name must be between {min} and {max} characters")
@NotBlank(message = "product name cannot be blank")
public @interface ValidName {

    String message() default "Invalid name format";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
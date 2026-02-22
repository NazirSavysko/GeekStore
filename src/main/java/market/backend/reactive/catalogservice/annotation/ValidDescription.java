package market.backend.reactive.catalogservice.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
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
@Size( max = 1000, message = "product description must be at most {max} characters long")
public @interface ValidDescription {

    String message() default "Invalid name format";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
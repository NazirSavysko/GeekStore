package market.backend.reactive.catalogservice.exception_handler;

import lombok.val;
import market.backend.reactive.catalogservice.exception.ProductNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.support.WebExchangeBindException;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.ResponseEntity.status;

@ControllerAdvice
class GlobalProductControllerAdvice {

    @ExceptionHandler(ProductNotFoundException.class)
    public Mono<ResponseEntity<Map<String, String>>> handleProductNotFoundException(final ProductNotFoundException e) {

        val errors = Map.of(
                "message", e.getMessage(),
                "status", String.valueOf(NOT_FOUND.value()),
                "timestamp", LocalDateTime.now().toString()
        );

        return Mono.just(
                status(NOT_FOUND)
                        .body(errors)
        );
    }

    @ExceptionHandler(WebExchangeBindException.class)
    public Mono<ResponseEntity<Map<String, String>>> handleValidation(WebExchangeBindException ex) {

        final Map<String, String> errors = new HashMap<>();
        ex.getFieldErrors().forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));

        return Mono.just(
                status(BAD_REQUEST)
                        .body(errors)
        );
    }


}

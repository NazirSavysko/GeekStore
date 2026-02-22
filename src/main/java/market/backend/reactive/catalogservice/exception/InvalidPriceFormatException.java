package market.backend.reactive.catalogservice.exception;

public class InvalidPriceFormatException extends RuntimeException {
    public InvalidPriceFormatException(String message) {
        super(message);
    }
}

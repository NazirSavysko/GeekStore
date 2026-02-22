package market.backend.reactive.catalogservice.exception;

public class NegativePriceException extends RuntimeException {
    public NegativePriceException(String message) {
        super(message);
    }
}

package ai.conga.core.algorithm.exception;

public class SearchTimeoutException extends RuntimeException {
    public SearchTimeoutException() {
        super();
    }

    public SearchTimeoutException(String message) {
        super(message);
    }

    public SearchTimeoutException(String message, Throwable cause) {
        super(message, cause);
    }
}

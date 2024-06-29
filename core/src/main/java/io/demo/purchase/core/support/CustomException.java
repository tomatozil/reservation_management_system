package io.demo.purchase.core.support;

public class CustomException extends RuntimeException {
    private ErrorType errorType;

    public CustomException(ErrorType errorType) {
        super(errorType.getMessage());
        this.errorType = errorType;
    }

    public CustomException(ErrorType errorType, String message) {
        super(message);
        this.errorType = errorType;
    }
}

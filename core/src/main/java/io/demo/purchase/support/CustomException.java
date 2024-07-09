package io.demo.purchase.support;

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

    public int getStatusCode() {
        return errorType.getStatusCode();
    }
}

package io.demo.purchase.core.support;

import io.demo.purchase.core.domain.user.error.CoreDomainErrorType;

public class CustomException extends RuntimeException {
    private final ErrorType errorType;

    public CustomException(ErrorType errorType) {
        super(errorType.getMessage());
        this.errorType = errorType;
    }
}

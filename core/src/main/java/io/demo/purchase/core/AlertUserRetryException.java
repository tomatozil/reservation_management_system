package io.demo.purchase.core;

import io.demo.purchase.support.exception.CustomException;
import io.demo.purchase.support.exception.ErrorType;

public class AlertUserRetryException extends CustomException {
    public AlertUserRetryException(ErrorType errorType) {
        super(errorType);
    }

    public AlertUserRetryException(ErrorType errorType, String message) {
        super(errorType, message);
    }
}

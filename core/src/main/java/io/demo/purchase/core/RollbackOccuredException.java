package io.demo.purchase.core;

import io.demo.purchase.support.exception.CustomException;
import io.demo.purchase.support.exception.ErrorType;

public class RollbackOccuredException extends CustomException {
    public RollbackOccuredException(ErrorType errorType) {
        super(errorType);
    }

    public RollbackOccuredException(ErrorType errorType, String message) {
        super(errorType, message);
    }
}

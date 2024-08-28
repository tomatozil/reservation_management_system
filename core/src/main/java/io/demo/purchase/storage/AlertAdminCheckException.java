package io.demo.purchase.storage;

import io.demo.purchase.support.exception.CustomException;
import io.demo.purchase.support.exception.ErrorType;

public class AlertAdminCheckException extends CustomException {
    public AlertAdminCheckException(ErrorType errorType) {
        super(errorType);
    }

    public AlertAdminCheckException(ErrorType errorType, String message) {
        super(errorType, message);
    }
}

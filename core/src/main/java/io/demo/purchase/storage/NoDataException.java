package io.demo.purchase.storage;

import io.demo.purchase.support.exception.CustomException;
import io.demo.purchase.support.exception.ErrorType;

public class NoDataException extends CustomException {
    public NoDataException(ErrorType errorType) {
        super(errorType);
    }

    public NoDataException(ErrorType errorType, String message) {
        super(errorType, message);
    }
}

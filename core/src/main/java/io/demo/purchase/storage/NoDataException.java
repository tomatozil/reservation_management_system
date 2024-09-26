package io.demo.purchase.storage;

import io.demo.purchase.support.exception.CoreDomainErrorType;
import io.demo.purchase.support.exception.CustomException;
import io.demo.purchase.support.exception.ErrorType;

public class NoDataException extends CustomException {
    private static final CoreDomainErrorType ERROR_TYPE = CoreDomainErrorType.NOT_FOUND;

    public NoDataException() {
        super(ERROR_TYPE);
    }

    public NoDataException(String message) {
        super(ERROR_TYPE, message);
    }
}

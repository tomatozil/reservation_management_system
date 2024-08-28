package io.demo.purchase.core;

import io.demo.purchase.support.exception.CustomException;
import io.demo.purchase.support.exception.ErrorType;

public class PermissionIssueException extends CustomException {
    public PermissionIssueException(ErrorType errorType) {
        super(errorType);
    }

    public PermissionIssueException(ErrorType errorType, String message) {
        super(errorType, message);
    }
}

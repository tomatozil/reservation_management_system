package io.demo.purchase.core.api.controller;

import io.demo.purchase.core.AlertUserRetryException;
import io.demo.purchase.storage.NoDataException;
import io.demo.purchase.core.PermissionIssueException;
import io.demo.purchase.storage.AlertAdminCheckException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(PermissionIssueException.class)
    public ResponseEntity<String> handleAuthException(PermissionIssueException ex) {
        return ResponseEntity.status(ex.getStatusCode()).body(ex.getMessage());
    }

    @ExceptionHandler(AlertUserRetryException.class)
    public ResponseEntity<String> handleAlertUserRetryException(AlertUserRetryException ex) {
        return ResponseEntity.status(ex.getStatusCode()).body(ex.getMessage());
    }

    @ExceptionHandler(AlertAdminCheckException.class)
    public ResponseEntity<String> handleAlertAdminCheckException(AlertAdminCheckException ex) {
        return ResponseEntity.status(ex.getStatusCode()).body(ex.getMessage());
    }

    @ExceptionHandler(NoDataException.class)
    public ResponseEntity<String> handleNoDataException(NoDataException ex) {
        return ResponseEntity.status(ex.getStatusCode()).body(ex.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleUnexpectedException(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unexpected error occurred: " + ex.getMessage());
    }
}

package io.demo.purchase.core.api.controller;

import io.demo.purchase.core.AlertUserRetryException;
import io.demo.purchase.storage.NoDataException;
import io.demo.purchase.core.PermissionIssueException;
import io.demo.purchase.storage.AlertAdminCheckException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(PermissionIssueException.class)
    public ResponseEntity<String> handleAuthException(PermissionIssueException ex) {
        log.info("[auth exception 발생] {}", ex.getMessage()); //TODO: 진짜 log로 남기기
        return ResponseEntity.status(ex.getStatusCode()).body(ex.getMessage());
    }

    @ExceptionHandler(AlertUserRetryException.class)
    public ResponseEntity<String> handleAlertUserRetryException(AlertUserRetryException ex) {
        log.info("[user 실수 exception 발생] {}\n\t{}", ex.getMessage(), ex.getStackTrace());
        return ResponseEntity.status(ex.getStatusCode()).body(ex.getMessage());
    }

    @ExceptionHandler(AlertAdminCheckException.class)
    public ResponseEntity<String> handleAlertAdminCheckException(AlertAdminCheckException ex) {
        log.info("[admin 실수 exception 발생] {}", ex.getMessage());
        return ResponseEntity.status(ex.getStatusCode()).body(ex.getMessage());
    }

    @ExceptionHandler(NoDataException.class)
    public ResponseEntity<String> handleNoDataException(NoDataException ex) {
        log.info("[데이터 유무 exception 발생] {}", ex.getMessage());
        return ResponseEntity.status(ex.getStatusCode()).body(ex.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleUnexpectedException(Exception ex) {
        log.info("[알 수 없는 exception] {}", ex.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unexpected error occurred: " + ex.getMessage());
    }
}

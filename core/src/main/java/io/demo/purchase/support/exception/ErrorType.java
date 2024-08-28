package io.demo.purchase.support.exception;

public interface ErrorType {
    Integer getStatusCode();
    String getMessage();
}

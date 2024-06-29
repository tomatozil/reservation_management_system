package io.demo.purchase.core.domain.error;

import io.demo.purchase.core.support.ErrorType;
import org.springframework.http.HttpStatus;

public enum CoreDomainErrorType implements ErrorType {

    BAD_REQUEST_DATA(HttpStatus.BAD_REQUEST, "요청 데이터가 올바르지 않습니다");

    private Integer statusCode;
    private String message;


    CoreDomainErrorType(HttpStatus status, String message) {
        this.statusCode = status.value();
        this.message = message;
    }

    @Override
    public Integer getStatusCode() {
        return statusCode;
    }

    @Override
    public String getMessage() {
        return message;
    }
}

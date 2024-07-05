package io.demo.purchase.core.domain.error;

import io.demo.purchase.support.ErrorType;
import org.springframework.http.HttpStatus;

public enum CoreDomainErrorType implements ErrorType {

    UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "인증에 실패했습니다"),
    BAD_REQUEST_DATA(HttpStatus.BAD_REQUEST, "요청 데이터가 올바르지 않습니다"),
    NOT_FOUND(HttpStatus.NOT_FOUND, "요청 데이터를 찾지 못했습니다");

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

package io.demo.purchase.support;

public enum BookingStatus {
    CONFIRMED("확정"),
    CANCELED("취소");

    private String status;

    BookingStatus(String status) {
        this.status = status;
    }
}

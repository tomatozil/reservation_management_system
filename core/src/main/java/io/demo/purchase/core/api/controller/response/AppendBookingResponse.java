package io.demo.purchase.core.api.controller.response;

import lombok.Getter;

@Getter
public class AppendBookingResponse {
    long bookingId;

    public AppendBookingResponse(long bookingId) {
        this.bookingId = bookingId;
    }
}

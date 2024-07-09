package io.demo.purchase.core.api.controller;

import io.demo.purchase.core.api.controller.response.AppendBookingResponse;
import io.demo.purchase.core.domain.booking.BookingService;
import io.demo.purchase.core.domain.user.User;
import io.demo.purchase.support.argumentresolver.AuthorizedUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookingController {

    private final BookingService bookingService;

    @Autowired
    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @PostMapping("/booking/{slotId}")
    public AppendBookingResponse bookSlot(@AuthorizedUser User user, @PathVariable("slotId") Long slotId) {
        long bookingId = bookingService.add(user.getId(), slotId);
        return new AppendBookingResponse(bookingId);
    }
}

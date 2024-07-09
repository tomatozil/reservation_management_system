package io.demo.purchase.core.domain.booking;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookingService {

    private final BookingAppender bookingAppender;

    @Autowired
    public BookingService(BookingAppender bookingAppender) {
        this.bookingAppender = bookingAppender;
    }

    public long add(long userId, long slotId) {
        // userid, slotid append
        return bookingAppender.append(userId, slotId);
    }
}

package io.demo.purchase.core.domain.booking;

import io.demo.purchase.support.BookingStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookingService {

    private final BookingWriter bookingWriter;

    @Autowired
    public BookingService(BookingWriter bookingWriter) {
        this.bookingWriter = bookingWriter;
    }

    public long add(long userId, long slotId) {
        // userid, slotid append
        return bookingWriter.append(userId, slotId);
//        return bookingWriter.appendWithLocalCache(userId, slotId);
    }

    public void cancel(long userId, long slotId) {
        bookingWriter.cancelWithLocalCache(userId, slotId);
    }
}

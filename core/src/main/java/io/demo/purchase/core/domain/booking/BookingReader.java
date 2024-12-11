package io.demo.purchase.core.domain.booking;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class BookingReader {

    private final BookingRepository bookingRepository;

    @Autowired
    public BookingReader(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    public Optional<Long> find(long userId, long slotId) {
        return bookingRepository.find(userId, slotId);
    }

    public long count(long slotId) {
        return bookingRepository.count(slotId);
    }
}

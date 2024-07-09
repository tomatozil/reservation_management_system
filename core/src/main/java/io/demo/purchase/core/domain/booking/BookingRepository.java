package io.demo.purchase.core.domain.booking;

import org.springframework.stereotype.Repository;

@Repository
public interface BookingRepository {
    long add(long userId, long slotId);
    Booking find(long userId, long slotId);
    long count(long slotId);
}

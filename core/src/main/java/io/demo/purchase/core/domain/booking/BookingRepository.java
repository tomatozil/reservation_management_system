package io.demo.purchase.core.domain.booking;

import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BookingRepository {
    long add(long userId, long slotId);
    Optional<Booking> find(long userId, long slotId);
    long count(long slotId);
}

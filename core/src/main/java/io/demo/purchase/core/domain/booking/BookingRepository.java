package io.demo.purchase.core.domain.booking;

import io.demo.purchase.support.BookingStatus;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BookingRepository {
    long add(long userId, long slotId);
    @Lock(LockModeType.PESSIMISTIC_READ)
    Optional<Long> find(long userId, long slotId);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    void updateStatus(long bookingId, BookingStatus to);
    long count(long slotId);
}

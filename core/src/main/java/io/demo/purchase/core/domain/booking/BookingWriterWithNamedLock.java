package io.demo.purchase.core.domain.booking;

import io.demo.purchase.core.AlertUserRetryException;
import io.demo.purchase.core.domain.slot.Slot;
import io.demo.purchase.core.domain.slot.SlotReader;
import io.demo.purchase.core.domain.stock.Stock;
import io.demo.purchase.core.domain.stock.StockReader;
import io.demo.purchase.core.domain.stock.StockWriter;
import io.demo.purchase.support.exception.CoreDomainErrorType;
import java.util.Optional;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Component
public class BookingWriterWithNamedLock {
    private final SlotReader slotReader;
    private final StockReader stockReader;
    private final StockWriter stockWriter;
    private final BookingReader bookingReader;
    private final BookingRepository bookingRepository;

    public BookingWriterWithNamedLock(SlotReader slotReader, StockReader stockReader, StockWriter stockWriter,
                                      BookingReader bookingReader, BookingRepository bookingRepository) {
        this.slotReader = slotReader;
        this.stockReader = stockReader;
        this.stockWriter = stockWriter;
        this.bookingReader = bookingReader;
        this.bookingRepository = bookingRepository;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, timeout = 1)
    protected long bookWithNamedLock(long userId, long slotId) {
        Slot slot = slotReader.find(slotId);
        // 예약 내역 확인
        Optional<Long> optBookingId = bookingReader.find(userId, slot.getId());
        if (optBookingId.isPresent()) {
            throw new AlertUserRetryException(CoreDomainErrorType.REQUEST_FAILED, "예약 내역이 존재합니다");
        }

        Stock stock = stockReader.find(slot.getId());

        stockWriter.increaseStock(stock.getId());

        return bookingRepository.add(userId, slotId);
    }
}

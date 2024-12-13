package io.demo.purchase.core.domain.booking;

import io.demo.purchase.core.AlertUserRetryException;
import io.demo.purchase.core.domain.slot.Slot;
import io.demo.purchase.core.domain.slot.SlotReader;
import io.demo.purchase.support.exception.CoreDomainErrorType;
import io.demo.purchase.core.domain.stock.Stock;
import io.demo.purchase.core.domain.stock.StockReader;
import io.demo.purchase.core.domain.stock.StockWriter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionTemplate;

@Slf4j
@Component
public class BookingWriter {
    private final SlotReader slotReader;
    private final StockReader stockReader;
    private final StockWriter stockWriter;
    private final BookingReader bookingReader;
    private final BookingRepository bookingRepository;

    @Autowired
    public BookingWriter(
            StockReader stockReader,
            StockWriter stockWriter,
            BookingReader bookingReader,
            BookingRepository bookingRepository,
            SlotReader slotReader, TransactionTemplate transactionTemplate, SlotReader slotReader1) {
        this.stockReader = stockReader;
        this.stockWriter = stockWriter;
        this.bookingReader = bookingReader;
        this.bookingRepository = bookingRepository;
        this.slotReader = slotReader1;
    }

    @Transactional
    public long append(long userId, long slotId) {
        try {
            stockReader.getNamedLock();

            Slot slot = slotReader.find(slotId);
            // 예약 내역 확인
            Optional<Long> optBookingId = bookingReader.find(userId, slot.getId());
            if (optBookingId.isPresent()) {
                throw new AlertUserRetryException(CoreDomainErrorType.REQUEST_FAILED, "예약 내역이 존재합니다");
            }

            Stock stock = stockReader.find(slot.getId());

            stockWriter.increaseStock(stock.getId());

            return bookingRepository.add(userId, slotId);
        } finally {
            stockReader.releaseNamedLock();
        }
    }
}

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
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionTemplate;

@Slf4j
@Component
public class BookingWriter {
    private final StockReader stockReader;
    private final BookingWriterWithNamedLock bookingWriterWithNamedLock;

    @Autowired
    public BookingWriter(
            StockReader stockReader,
            BookingWriterWithNamedLock bookingWriterWithNamedLock) {
        this.stockReader = stockReader;
        this.bookingWriterWithNamedLock = bookingWriterWithNamedLock;
    }

    @Transactional()
    public long append(long userId, long slotId) {
        try {
            stockReader.getNamedLock();
            return bookingWriterWithNamedLock.bookWithNamedLock(userId, slotId);
        } finally {
            stockReader.releaseNamedLock();
        }
    }
}

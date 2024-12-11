package io.demo.purchase.core.domain.booking;
;
import io.demo.purchase.core.AlertUserRetryException;
import io.demo.purchase.core.RollbackOccuredException;
import io.demo.purchase.core.domain.slot.Slot;
import io.demo.purchase.core.domain.slot.SlotReader;
import io.demo.purchase.storage.NoDataException;
import io.demo.purchase.support.BookingStatus;
import io.demo.purchase.support.exception.CoreDomainErrorType;
import io.demo.purchase.core.domain.stock.Stock;
import io.demo.purchase.core.domain.stock.StockReader;
import io.demo.purchase.core.domain.stock.StockWriter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Component
public class BookingWriter {
    private final StockReader stockReader;
    private final StockWriter stockWriter;
    private final BookingReader bookingReader;
    private final BookingRepository bookingRepository; // writer ?

    private final SlotReader slotReader;

    volatile ConcurrentHashMap<Long, Long> stockTable;

    @Autowired
    public BookingWriter(
            StockReader stockReader,
            StockWriter stockWriter,
            BookingReader bookingReader,
            BookingRepository bookingRepository,
            SlotReader slotReader) {
        this.stockReader = stockReader;
        this.stockWriter = stockWriter;
        this.bookingReader = bookingReader;
        this.bookingRepository = bookingRepository;
        this.slotReader = slotReader;
        this.stockTable = new ConcurrentHashMap<>();
    }

    public long appendWithLocalCache(long userId, long slotId) {
        // check rebook (already in or not)
        Optional<Long> optBookingId = bookingReader.find(userId, slotId);
        if (optBookingId.isPresent())
            throw new AlertUserRetryException(CoreDomainErrorType.REQUEST_FAILED, "예약 내역이 존재합니다");

        Slot slot = slotReader.find(slotId);
        stockTable.compute(slot.getId(), (key, value) -> {
            if (value == null)
                return 1L;
            else if (value < slot.getTotal()) {
                return value + 1L;
            }
            else
                throw new AlertUserRetryException(CoreDomainErrorType.REQUEST_FAILED, "인원 초과로 예약이 불가능합니다");
        });

        return bookingRepository.add(userId, slotId);
    }

    public void cancelWithLocalCache(long userId, long slotId) {
        long bookingId = bookingReader.find(userId, slotId)
                .orElseThrow(() -> new NoDataException("예약 내역이 존재하지 않아서 취소 할 수 없습니다"));

        stockTable.computeIfPresent(slotId, (key, value) -> value > 0 ? value - 1 : value);

        bookingRepository.updateStatus(bookingId, BookingStatus.CANCELED);
    }

    @Transactional
    public long append(long userId, long slotId) {
        // 예약 내역 확인
        Optional<Long> optBookingId = bookingReader.find(userId, slotId);
        if (optBookingId.isPresent()) {
            throw new AlertUserRetryException(CoreDomainErrorType.REQUEST_FAILED, "예약 내역이 존재합니다");
        }

        // 재고 확인
        Stock stock = stockReader.findStock(slotId)
                .filter(s -> s.getStock() < s.getTotal())
                .orElseThrow(() -> new AlertUserRetryException(CoreDomainErrorType.REQUEST_FAILED, "인원 초과로 예약이 불가능합니다"));

        // 재고 업데이트
        stock.setStock(stock.getStock() + 1);
        stockWriter.updateStock(stock);

        return bookingRepository.add(userId, slotId);
    }
}

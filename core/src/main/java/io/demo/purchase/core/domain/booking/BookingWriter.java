package io.demo.purchase.core.domain.booking;
;
import io.demo.purchase.core.AlertUserRetryException;
import io.demo.purchase.support.exception.CoreDomainErrorType;
import io.demo.purchase.core.domain.stock.Stock;
import io.demo.purchase.core.domain.stock.StockReader;
import io.demo.purchase.core.domain.stock.StockWriter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Slf4j
@Component
public class BookingWriter {
    private final StockReader stockReader;
    private final StockWriter stockWriter;
    private final BookingReader bookingReader;
    private final BookingRepository bookingRepository; // writer ?

    @Autowired
    public BookingWriter(
            StockReader stockReader,
            StockWriter stockWriter,
            BookingReader bookingReader,
            BookingRepository bookingRepository
    ) {
        this.stockReader = stockReader;
        this.stockWriter = stockWriter;
        this.bookingReader = bookingReader;
        this.bookingRepository = bookingRepository;
    }


    public long append(long userId, long slotId) {
        // check rebook (already in or not)
        Optional<Booking> optBooking = bookingReader.find(userId, slotId);
        if (optBooking.isPresent())
            throw new AlertUserRetryException(CoreDomainErrorType.REQUEST_FAILED, "예약 내역이 존재합니다");

        long bookingId;
        synchronized (this) {
            // get current stock and total
            // filtering if condition is true
            Stock stock = stockReader.findStock(slotId)
                    .filter(s -> s.getStock() < s.getTotal())
                    .orElseThrow(() -> new AlertUserRetryException(CoreDomainErrorType.REQUEST_FAILED, "인원 초과로 예약이 불가능합니다"));

            // update stock entity
            stock.setStock(stock.getStock()+1);
            stockWriter.updateStock(stock);
        }
        // user id, slot id write
        bookingId = bookingRepository.add(userId, slotId);

        return bookingId;
    }
}

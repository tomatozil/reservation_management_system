package io.demo.purchase.core.domain.booking;
;
import io.demo.purchase.core.AlertUserRetryException;
import io.demo.purchase.core.RollbackOccuredException;
import io.demo.purchase.support.exception.CoreDomainErrorType;
import io.demo.purchase.core.domain.stock.Stock;
import io.demo.purchase.core.domain.stock.StockReader;
import io.demo.purchase.core.domain.stock.StockWriter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.Optional;

@Slf4j
@Component
public class BookingWriter {
    private final StockReader stockReader;
    private final StockWriter stockWriter;
    private final BookingReader bookingReader;
    private final BookingRepository bookingRepository; // writer ?

    private final TransactionTemplate transactionTemplate;

    @Autowired
    public BookingWriter(
            StockReader stockReader,
            StockWriter stockWriter,
            BookingReader bookingReader,
            BookingRepository bookingRepository,
            TransactionTemplate transactionTemplate
    ) {
        this.stockReader = stockReader;
        this.stockWriter = stockWriter;
        this.bookingReader = bookingReader;
        this.bookingRepository = bookingRepository;
        this.transactionTemplate = transactionTemplate;
    }


    public long append(long userId, long slotId) {
        // check rebook (already in or not)
        Optional<Long> optBookingId = bookingReader.find(userId, slotId);
        if (optBookingId.isPresent())
            throw new AlertUserRetryException(CoreDomainErrorType.REQUEST_FAILED, "예약 내역이 존재합니다");

//        synchronized (this) {
//            // get current stock and total
//            // filtering if condition is true
//            Stock stock = stockReader.findStock(slotId)
//                    .filter(s -> s.getStock() < s.getTotal())
//                    .orElseThrow(() -> new AlertUserRetryException(CoreDomainErrorType.REQUEST_FAILED, "인원 초과로 예약이 불가능합니다"));
//
//            // update stock entity
//            stock.setStock(stock.getStock()+1);
//            stockWriter.updateStock(stock);
//        }

        // Use transaction template for stock update and booking creation
        Long bookingId = Optional.ofNullable(transactionTemplate.execute(status -> {

            Stock stock = stockReader.findStock(slotId)
                    .filter(s -> s.getStock() < s.getTotal())
                    .orElseThrow(() -> new AlertUserRetryException(CoreDomainErrorType.REQUEST_FAILED, "인원 초과로 예약이 불가능합니다"));

            // update stock entity
            stock.setStock(stock.getStock() + 1);
            stockWriter.updateStock(stock);

            // user id, slot id write
            long result = bookingRepository.add(userId, slotId);
            return result;
        })).orElseThrow(() -> new RollbackOccuredException(CoreDomainErrorType.NOT_ACCEPTABLE, "예약 처리 도중 문제가 발생했습니다"));

        return bookingId;
    }
}

package io.demo.purchase.core.domain.booking;
;
import io.demo.purchase.core.domain.error.CoreDomainErrorType;
import io.demo.purchase.core.domain.stock.StockReader;
import io.demo.purchase.support.CustomException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class BookingAppender {

    private final StockReader stockReader;
    private final BookingReader bookingReader;
    private final BookingRepository bookingRepository; // writer ?

    @Autowired
    public BookingAppender(StockReader stockReader, BookingReader bookingReader, BookingRepository bookingRepository) {
        this.stockReader = stockReader;
        this.bookingReader = bookingReader;
        this.bookingRepository = bookingRepository;
    }

    public long append(long userId, long slotId) {
        // total stock check
        long total = stockReader.findQuantity(slotId);

        // check rebook (already in or not)
        try {
            Booking booking = bookingReader.find(userId, slotId);
        } catch (CustomException e) {
            if (e.getStatusCode() != HttpStatus.BAD_REQUEST.value()) {
                throw e;
            }
        }

        // current booking count
        long cur = bookingReader.count(slotId);

        if (cur >= total) {
            throw new CustomException(CoreDomainErrorType.REQUEST_FAILED, "인원 초과로 예약이 불가능합니다");
        }

        // user id, slot id write
        long bookingId = bookingRepository.add(userId, slotId);
        return bookingId;
    }
}

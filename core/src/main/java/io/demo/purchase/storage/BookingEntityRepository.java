package io.demo.purchase.storage;

import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import io.demo.purchase.core.domain.booking.Booking;
import io.demo.purchase.core.domain.booking.BookingRepository;
import io.demo.purchase.support.BookingStatus;
import io.demo.purchase.support.exception.CoreDomainErrorType;
import jakarta.persistence.LockModeType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
class BookingEntityRepository extends QuerydslRepositorySupport implements BookingRepository {

    private final BookingJpaRepository bookingJpaRepository;
    private final JPAQueryFactory jpaQueryFactory;
    private static final QBookingEntity bookingEntity = QBookingEntity.bookingEntity;

    @Autowired
    public BookingEntityRepository(BookingJpaRepository bookingJpaRepository, JPAQueryFactory jpaQueryFactory) {
        super(BookingEntity.class);
        this.bookingJpaRepository = bookingJpaRepository;
        this.jpaQueryFactory = jpaQueryFactory;
    }

    @Override
    public long add(long userId, long slotId) {
        BookingEntity booking = bookingJpaRepository.save(BookingEntity.of(userId, slotId));
        return booking.getId();
    }

    @Override
    public Optional<Long> find(long userId, long slotId) {
        return Optional.ofNullable(jpaQueryFactory.select(bookingEntity.id)
                .from(bookingEntity)
                .where(bookingEntity.userId.eq(userId)
                        .and(bookingEntity.slotId.eq(slotId))
                        .and(bookingEntity.status.eq(BookingStatus.CONFIRMED)))
//                        .setLockMode(LockModeType.PESSIMISTIC_WRITE)
                .fetchOne());
    }

    @Override
    public void updateStatus(long bookingId, BookingStatus to) {
        BookingEntity bookingEntity = bookingJpaRepository.findById(bookingId)
                .orElseThrow(() -> new NoDataException("요청 예약 내역을 찾지 못했습니다"));

        bookingEntity.updateStatus(to);
        bookingJpaRepository.save(bookingEntity);
    }


    @Override
    public long count(long slotId) {
        Long total = Optional.ofNullable(jpaQueryFactory.select(Expressions.ONE.count())
                .from(bookingEntity)
                .where(bookingEntity.slotId.eq(slotId))
                .fetchOne())
                .orElse(0L);

        return total;
    }
}

package io.demo.purchase.core.domain.booking;

import io.demo.purchase.storage.*;
import io.demo.purchase.support.CustomException;
import io.demo.purchase.support.WorkoutType;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BookingAppenderTest {

    private static final Logger log = LoggerFactory.getLogger(BookingAppenderTest.class);
    BookingAppender bookingAppender;
    UserJpaRepository userJpaRepository;
    SlotJpaRepository slotJpaRepository;
    StockJpaRepository stockJpaRepository;
    BookingJpaRepository bookingJpaRepository;

    @Autowired
    public BookingAppenderTest(BookingAppender bookingAppender,
                               UserJpaRepository userJpaRepository, SlotJpaRepository slotJpaRepository, StockJpaRepository stockJpaRepository, BookingJpaRepository bookingJpaRepository) {
        this.bookingAppender = bookingAppender;
        this.userJpaRepository = userJpaRepository;
        this.slotJpaRepository = slotJpaRepository;
        this.stockJpaRepository = stockJpaRepository;
        this.bookingJpaRepository = bookingJpaRepository;
    }

    // slot1
    SlotEntity slot;
    StockEntity stock;
    //user5
    UserEntity user;

    @BeforeEach
    void init() {
        // user1 -> coach
        // slot1 -> stock total 2

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime localDateTime = LocalDateTime.parse("2024-08-30 14:00:00", formatter);

        slot = this.slotJpaRepository.save(SlotEntity.builder()
                .coachId(1L).workoutType(WorkoutType.CARDIO).eventDatetime(localDateTime).price(20000L)
                .build());

        stock = this.stockJpaRepository.save(StockEntity.builder()
                .slotId(slot.getId())
                .total(1L)
                .build());

        user = this.userJpaRepository.save(UserEntity.builder()
                .name("cookie")
                .email("cookie@cookie.com")
                .password("fake-password")
                .build());
    }

    @AfterEach
    void cleanUp() {
        slotJpaRepository.deleteById(slot.getId());
        stockJpaRepository.deleteById(stock.getId());
    }

    @Test
    @DisplayName("slot 예약에 성공")
    void appendTestSuccess() {
        // user3 -> book slot1 ok
        log.info("slotId: "+ slot.getId());

        Long bookingId = bookingAppender.append(3L, slot.getId());

        assertThat(bookingId).isNotNull();

        bookingJpaRepository.deleteById(bookingId);
    }

    @Test
    @DisplayName("slot 예약에 실패함. 정원이 다 참")
    void appendTestFailed() {
        // user 3 -> book slot1 ok
        // user 5 -> member and book slot1 failed
        Long bookingId = bookingAppender.append(3L, slot.getId());

        assertThatThrownBy(() -> bookingAppender.append(user.getId(), slot.getId()))
                .isInstanceOf(CustomException.class)
                .hasMessage("인원 초과로 예약이 불가능합니다");

        bookingJpaRepository.deleteById(bookingId);
    }

    @Test
    @DisplayName("두명이 동시에?! 남은 한자리룰 예약할 때")
    void appendTestConcurrency() {
        // test
    }
}
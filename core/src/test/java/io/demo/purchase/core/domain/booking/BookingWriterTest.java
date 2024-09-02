package io.demo.purchase.core.domain.booking;

import io.demo.purchase.storage.*;
import io.demo.purchase.support.exception.CustomException;
import io.demo.purchase.support.WorkoutType;

import org.junit.jupiter.api.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@TestPropertySource(properties = "JWT_SECRET_KEY=Wq7Yv0DkeG1X2gZx4z+qLpEkR6T8zUdjT2fWf8rNcY=")
@SpringBootTest
class BookingWriterTest {

    private static final Logger log = LoggerFactory.getLogger(BookingWriterTest.class);
    BookingWriter bookingWriter;
    UserJpaRepository userJpaRepository;
    SlotJpaRepository slotJpaRepository;
    StockJpaRepository stockJpaRepository;
    BookingJpaRepository bookingJpaRepository;

    @Autowired
    public BookingWriterTest(BookingWriter bookingWriter,
                             UserJpaRepository userJpaRepository, SlotJpaRepository slotJpaRepository, StockJpaRepository stockJpaRepository, BookingJpaRepository bookingJpaRepository) {
        this.bookingWriter = bookingWriter;
        this.userJpaRepository = userJpaRepository;
        this.slotJpaRepository = slotJpaRepository;
        this.stockJpaRepository = stockJpaRepository;
        this.bookingJpaRepository = bookingJpaRepository;
    }

    // slot1
    SlotEntity slot;
    StockEntity stock;
    //user5
    UserEntity user1;
    UserEntity user2;
    UserEntity user3;
    UserEntity user4;
    UserEntity user5;
    UserEntity user6;
    UserEntity user7;
    UserEntity user8;
    UserEntity user9;
    UserEntity user10;
    List<UserEntity> users = new ArrayList<>();

    @BeforeEach
    @Transactional
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
                .stock(0L)
                .total(3L)
                .build());

        user1 = this.userJpaRepository.save(UserEntity.builder()
                .name("cookie")
                .email("cookie@cookie.com")
                .password("fake-password")
                .build());
        user2 = this.userJpaRepository.save(UserEntity.builder()
                .name("phamhanni")
                .email("phamhanni@phamhanni.com")
                .password("fake-password")
                .build());
        user3 = this.userJpaRepository.save(UserEntity.builder()
                .name("daniel")
                .email("daniel@daniel.com")
                .password("fake-password")
                .build());
        user4 = this.userJpaRepository.save(UserEntity.builder()
                .name("minji")
                .email("minji@minji.com")
                .password("fake-password")
                .build());
        user5 = this.userJpaRepository.save(UserEntity.builder()
                .name("karina")
                .email("karina@karina.com")
                .password("fake-password")
                .build());
        user6 = this.userJpaRepository.save(UserEntity.builder()
                        .name("jjh")
                        .email("jjh@jjh.com")
                        .password("fake-password")
                .build());
        user7 = this.userJpaRepository.save(UserEntity.builder()
                .name("yjy")
                .email("yjy@yjy.com")
                .password("fake-password")
                .build());
        user8 = this.userJpaRepository.save(UserEntity.builder()
                .name("abc")
                .email("abc@abc.com")
                .password("fake-password")
                .build());
        user9 = this.userJpaRepository.save(UserEntity.builder()
                .name("bbz")
                .email("bbz@bbz.com")
                .password("fake-password")
                .build());
        user10 = this.userJpaRepository.save(UserEntity.builder()
                .name("nmix")
                .email("nmix@nmix.com")
                .password("fake-password")
                .build());
        users.add(user1);
        users.add(user2);
        users.add(user3);
        users.add(user4);
        users.add(user5);
        users.add(user6);
        users.add(user7);
        users.add(user8);
        users.add(user9);
        users.add(user10);
    }

    @Test
    @DisplayName("slot 예약에 성공")
    void appendTestSuccess() {
        // user3 -> book slot1 ok

        Long bookingId = bookingWriter.append(user3.getId(), slot.getId());

        assertThat(bookingId).isNotNull();
    }

    @Test
    @DisplayName("slot 예약에 실패함. 정원이 다 참")
    void appendTestFailed() {
        // user 3 -> book slot1 ok
        // user 5 -> member and book slot1 failed
        Long bookingId2 = bookingWriter.append(user2.getId(), slot.getId());
        Long bookingId1 = bookingWriter.append(user3.getId(), slot.getId());

        assertThatThrownBy(() -> bookingWriter.append(user1.getId(), slot.getId()))
                .isInstanceOf(CustomException.class)
                .hasMessage("인원 초과로 예약이 불가능합니다");
    }

    @Test
    @DisplayName("여러명이 동시에?! 남은 자리를 예약할 때")
    void appendConcurrencyTest() throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(10);

        List<ParticipateWorker> workers = users.stream()
                .map((user) -> new ParticipateWorker(user, countDownLatch))
                .toList();

        workers.forEach((worker) -> new Thread(worker).start());
        countDownLatch.await();

        List<BookingEntity> bookings = bookingJpaRepository.findBySlotId(slot.getId());

        long size = bookings.size();
        assertThat(size).isEqualTo(3);
    }

    private class ParticipateWorker implements Runnable {
        private UserEntity user;
        private CountDownLatch countDownLatch;

        public ParticipateWorker(UserEntity user, CountDownLatch countDownLatch) {
            this.user = user;
            this.countDownLatch = countDownLatch;
        }

        @Override
        public void run() {
            try {
                bookingWriter.append(this.user.getId(), slot.getId());
            } catch (Exception e) {
                log.info("Exception in thread {} io.demo.purchase.support.error.CustomException: {}", Thread.currentThread().getName(), e.getMessage());
            }
            countDownLatch.countDown();
        }
    }
}
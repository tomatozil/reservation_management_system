package io.demo.purchase.core.domain.booking;

import static org.assertj.core.api.Assertions.assertThat;

import groovy.util.logging.Slf4j;
//import io.demo.purchase.storage.UserEntity;
import java.util.concurrent.CountDownLatch;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@SpringBootTest
@TestPropertySource(properties = {
        "JWT_SECRET_KEY=Wq7Yv0DkeG1X2gZx4z+qLpEkR6T8zUdjT2fWf8rNcY="
})
class BookingWriterTest {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    BookingWriter bookingWriter;

    @Test
    @Transactional
    void 정상_예약_테스트() {
        assertThat(bookingWriter.append(1, 1)).isEqualTo(1);
    }

    @Test
    @Transactional
    void 동시_예약_테스트() {

    }

//    private class Worker implements Runnable {
//        private UserEntity user;
//        private CountDownLatch countDownLatch;
//
//        public Worker(UserEntity user, CountDownLatch countDownLatch) {
//            this.user = user;
//            this.countDownLatch = countDownLatch;
//        }
//
//        @Override
//        public void run() {
//            try {
//                bookingWriter.append(this.user.getId(), 1);
//            } catch (Exception e) {
//                log.info("Exception in thread {} io.demo.purchase.support.error.CustomException: {}", Thread.currentThread().getName(), e.getMessage());
//            }
//            countDownLatch.countDown();
//        }
//    }
}
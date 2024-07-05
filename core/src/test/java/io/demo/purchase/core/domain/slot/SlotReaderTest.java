package io.demo.purchase.core.domain.slot;

import groovy.util.logging.Slf4j;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Slf4j
@SpringBootTest
@AutoConfigureTestEntityManager
class SlotReaderTest {

    private static final Logger log = LoggerFactory.getLogger(SlotReaderTest.class);
    @Autowired
    SlotReader slotReader;

    @Autowired
    TestEntityManager em;

    @Test
    @Transactional
    void findList() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        LocalDate date = LocalDate.parse("20240710", formatter);
        LocalDateTime localDateTime = date.atStartOfDay();
        log.info(String.valueOf(localDateTime));
        List<Slot> slots = slotReader.findList(localDateTime);
        log.info(slots.toString());
    }

    @Test
    void find() {
    }
}
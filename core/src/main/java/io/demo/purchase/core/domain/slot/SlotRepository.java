package io.demo.purchase.core.domain.slot;

import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface SlotRepository {
    List<SlotSimple> findList(LocalDate date);
    Slot find(Long slotId);
}

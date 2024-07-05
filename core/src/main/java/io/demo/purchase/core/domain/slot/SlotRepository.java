package io.demo.purchase.core.domain.slot;

import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface SlotRepository {
    List<Slot> findList(LocalDateTime date);
    SlotDetail find(Long slotId);
}

package io.demo.purchase.core.domain.slot;

import io.demo.purchase.storage.SlotEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class SlotReader {

    private final SlotRepository slotRepository;

    @Autowired
    public SlotReader(SlotRepository slotRepository) {
        this.slotRepository = slotRepository;
    }

    public List<Slot> findList(LocalDateTime date) {
        return slotRepository.findList(date);
    }

    public SlotDetail find(Long slotId) {
        return slotRepository.find(slotId);
    }
}

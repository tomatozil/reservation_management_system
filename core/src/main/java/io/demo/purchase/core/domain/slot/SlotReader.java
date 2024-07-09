package io.demo.purchase.core.domain.slot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public class SlotReader {

    private final SlotRepository slotRepository;

    @Autowired
    public SlotReader(SlotRepository slotRepository) {
        this.slotRepository = slotRepository;
    }

    public List<SlotSimple> findList(LocalDate date) {
        return slotRepository.findList(date);
    }

    public Slot find(Long slotId) {
        return slotRepository.find(slotId);
    }
}

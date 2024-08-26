package io.demo.purchase.core.domain.slot;

import io.demo.purchase.admin.AddSlot;
import io.demo.purchase.support.WorkoutType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Component
public class SlotService {

    private final SlotReader slotReader;
    private final SlotWriter slotWriter;

    @Autowired
    public SlotService(SlotReader slotReader, SlotWriter slotWriter) {
        this.slotReader = slotReader;
        this.slotWriter = slotWriter;
    }

    // slot 리스트 (근데 이제 날짜별)
    public List<SlotSimple> findList(LocalDate date) {
        return slotReader.findList(date);
    }

    // slot 상세 정보
    public Slot find(Long slotId) {
        return slotReader.find(slotId);
    }

    public long add(AddSlot addSlotDto) {
        Slot slot = slotWriter.append(
                addSlotDto.getCoachId(),
                addSlotDto.getWorkoutType(),
                addSlotDto.getEventDatetime(),
                addSlotDto.getPrice());

        return slot.getId();
    }
}

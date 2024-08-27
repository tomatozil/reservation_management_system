package io.demo.purchase.core.domain.slot;

import io.demo.purchase.support.WorkoutType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class SlotWriter {

    private final SlotRepository slotRepository;

    @Autowired
    public SlotWriter(SlotRepository slotRepository) {
        this.slotRepository = slotRepository;
    }

    public Slot append(
            long coachId,
            WorkoutType workoutType,
            LocalDateTime eventDatetime,
            long price
    ) {
        return slotRepository.add(coachId, workoutType, eventDatetime, price);
    }
}

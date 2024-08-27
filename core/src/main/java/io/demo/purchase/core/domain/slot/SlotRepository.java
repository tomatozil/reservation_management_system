package io.demo.purchase.core.domain.slot;

import io.demo.purchase.support.WorkoutType;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface SlotRepository {
    Slot add(long coachId, WorkoutType workoutType, LocalDateTime eventDatetime, long price);
    List<SlotSimple> findList(LocalDate date);
    Slot find(Long slotId);
}

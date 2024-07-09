package io.demo.purchase.core.domain.slot;

import io.demo.purchase.core.api.controller.response.FindSlotListResponse;
import io.demo.purchase.core.api.controller.response.FindSlotResponse;
import io.demo.purchase.support.WorkoutType;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class Slot {
    long id;
    long coachId;
    WorkoutType workoutType;
    LocalDateTime eventTime;
    long price;

    public Slot(Long id, Long coachId, WorkoutType workoutType, LocalDateTime eventTime, Long price) {
        this.id = id;
        this.coachId = coachId;
        this.workoutType = workoutType;
        this.eventTime = eventTime;
        this.price = price;
    }

    public FindSlotResponse toResponse() {
        return new FindSlotResponse(id, coachId, workoutType, eventTime, price);
    }
}

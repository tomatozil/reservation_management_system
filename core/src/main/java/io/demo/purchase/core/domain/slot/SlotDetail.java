package io.demo.purchase.core.domain.slot;

import io.demo.purchase.core.api.controller.response.FindSlotResponse;
import io.demo.purchase.support.WorkoutType;

import java.time.LocalDateTime;

public class SlotDetail {
    Long id;
    Long coachId;
    WorkoutType workoutType;
    LocalDateTime eventTime;
    Long price;

    public SlotDetail(Long id, Long coachId, WorkoutType workoutType, LocalDateTime eventTime, Long price) {
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

package io.demo.purchase.core.api.controller.response;

import io.demo.purchase.support.WorkoutType;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class FindSlotResponse {
    Long id;
    Long coachId;
    WorkoutType workoutType;
    LocalDateTime eventTime;
    Long price;

    public FindSlotResponse(Long id, Long coachId, WorkoutType workoutType, LocalDateTime eventTime, Long price) {
        this.id = id;
        this.coachId = coachId;
        this.workoutType = workoutType;
        this.eventTime = eventTime;
        this.price = price;
    }
}

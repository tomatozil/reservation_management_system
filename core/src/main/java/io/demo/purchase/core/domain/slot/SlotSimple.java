package io.demo.purchase.core.domain.slot;

import io.demo.purchase.support.WorkoutType;

import java.time.LocalDateTime;

public class SlotSimple {
    long id;
    WorkoutType workoutType;
    LocalDateTime eventTime;

    public SlotSimple(Long id, WorkoutType workoutType, LocalDateTime eventTime) {
        this.id = id;
        this.workoutType = workoutType;
        this.eventTime = eventTime;
    }
}

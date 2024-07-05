package io.demo.purchase.core.domain.slot;

import io.demo.purchase.support.WorkoutType;

import java.time.LocalDateTime;

public class Slot {
    Long id;
    WorkoutType workoutType;
    LocalDateTime eventTime;

    public Slot(Long id, WorkoutType workoutType, LocalDateTime eventTime) {
        this.id = id;
        this.workoutType = workoutType;
        this.eventTime = eventTime;
    }
}

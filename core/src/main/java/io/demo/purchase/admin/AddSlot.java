package io.demo.purchase.admin;

import io.demo.purchase.support.WorkoutType;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class AddSlot {
    Long coachId;
    WorkoutType workoutType;
    LocalDateTime eventDatetime;
    Long price;

    public AddSlot(Long coachId, WorkoutType workoutType, LocalDateTime eventDatetime, Long price) {
        this.coachId = coachId;
        this.workoutType = workoutType;
        this.eventDatetime = eventDatetime;
        this.price = price;
    }
}

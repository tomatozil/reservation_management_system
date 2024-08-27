package io.demo.purchase.admin;

import io.demo.purchase.support.WorkoutType;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class AddSlot {
    long coachId;
    WorkoutType workoutType;
    LocalDateTime eventDatetime;
    long price;
    long quantity;

    public AddSlot(long coachId, WorkoutType workoutType, LocalDateTime eventDatetime, long price, long quantity) {
        this.coachId = coachId;
        this.workoutType = workoutType;
        this.eventDatetime = eventDatetime;
        this.price = price;
        this.quantity = quantity;
    }
}

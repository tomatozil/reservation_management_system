package io.demo.purchase.admin;

import io.demo.purchase.support.WorkoutType;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Setter
public class AppendSlotRequest {
    Long coachId;
    WorkoutType workoutType;

    @DateTimeFormat(pattern = "yyyyMMdd")
    LocalDate eventDate;

    @DateTimeFormat(pattern = "HH")
    LocalTime eventTime;

    Long price;

    public AddSlot toAddSlot() {
        LocalDateTime eventDatetime = LocalDateTime.of(this.eventDate, this.eventTime);
        return new AddSlot(coachId, workoutType, eventDatetime, price);
    }
}

package io.demo.purchase.admin;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.demo.purchase.support.WorkoutType;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Setter
public class AppendSlotRequest {
    long coachId;
    WorkoutType workoutType;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyyMMdd")
    LocalDate eventDate;

    @JsonFormat(pattern = "HH")
    LocalTime eventTime;

    long price;
    long quantity;

    public AddSlot toAddSlot() {
        LocalDateTime eventDatetime = LocalDateTime.of(this.eventDate, this.eventTime).withSecond(0).withNano(0);;
        return new AddSlot(coachId, workoutType, eventDatetime, price, quantity);
    }
}

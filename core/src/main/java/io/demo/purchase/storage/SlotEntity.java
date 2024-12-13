package io.demo.purchase.storage;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.demo.purchase.core.domain.slot.SlotSimple;
import io.demo.purchase.core.domain.slot.Slot;
import io.demo.purchase.support.WorkoutType;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "slot")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
class SlotEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "coach_id", nullable = false)
    private Long coachId;

    @Column(name = "workout_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private WorkoutType workoutType;

    @Column(name = "event_datetime", nullable = false)
    private LocalDateTime eventDatetime;

    @Column(nullable = false)
    private Long price;

    public SlotEntity(long coachId, WorkoutType workoutType, LocalDateTime eventDatetime, long price) {
        this.coachId = coachId;
        this.workoutType = workoutType;
        this.eventDatetime = eventDatetime;
        this.price = price;
    }

    public SlotSimple toSlotSimple() {
        return new SlotSimple(id, workoutType, eventDatetime);
    }

    public Slot toSlot() {
        return new Slot(id, coachId, workoutType, eventDatetime, price);
    }

    public static SlotEntity of(long coachId, WorkoutType workoutType, LocalDateTime eventDatetime, long price) {
        return new SlotEntity(coachId, workoutType, eventDatetime, price);
    }
}

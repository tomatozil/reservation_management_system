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
public class SlotEntity extends BaseEntity {
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

//    booking 테이블에서 확인해볼 수 있는 정보인데 굳이 여기에까지?
//    @ElementCollection
//    @CollectionTable(name = "user", joinColumns = @JoinColumn(name = "user_id"))
//    private List<Long> applier = new ArrayList<>();


    public SlotSimple toSlotSimple() {
        return new SlotSimple(id, workoutType, eventDatetime);
    }

    public Slot toSlot() {
        return new Slot(id, coachId, workoutType, eventDatetime, price);
    }
}

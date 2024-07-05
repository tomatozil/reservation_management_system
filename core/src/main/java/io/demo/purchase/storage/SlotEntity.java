package io.demo.purchase.storage;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.demo.purchase.core.domain.slot.Slot;
import io.demo.purchase.core.domain.slot.SlotDetail;
import io.demo.purchase.support.WorkoutType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "slot")
@Getter
@Setter
@NoArgsConstructor
public class SlotEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "coach_id", nullable = false)
    private Long coachId;

    @Column(name = "workouttype", nullable = false)
    @Enumerated(EnumType.STRING)
    private WorkoutType workoutType;

    @Column(name = "event_datetime", nullable = false)
    @JsonFormat()
    private LocalDateTime eventDatetime; //colon 붙여야됨

    @Column(nullable = false)
    private Long price;

//    booking 테이블에서 확인해볼 수 있는 정보인데 굳이 여기에까지?
//    @ElementCollection
//    @CollectionTable(name = "user", joinColumns = @JoinColumn(name = "user_id"))
//    private List<Long> applier = new ArrayList<>();


    public Slot toSlotSimple() {
        return new Slot(id, workoutType, eventDatetime);
    }

    public SlotDetail toSlotDetail() {
        return new SlotDetail(id, coachId, workoutType, eventDatetime, price);
    }
}

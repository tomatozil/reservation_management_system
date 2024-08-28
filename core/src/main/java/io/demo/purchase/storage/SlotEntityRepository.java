package io.demo.purchase.storage;

import com.querydsl.jpa.impl.JPAQueryFactory;
import io.demo.purchase.support.exception.CoreDomainErrorType;
import io.demo.purchase.core.domain.slot.SlotSimple;
import io.demo.purchase.core.domain.slot.Slot;
import io.demo.purchase.core.domain.slot.SlotRepository;
import io.demo.purchase.support.WorkoutType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Repository
class SlotEntityRepository extends QuerydslRepositorySupport implements SlotRepository {

    private final SlotJpaRepository slotJpaRepository;
    private final JPAQueryFactory jpaQueryFactory;

    @Autowired
    public SlotEntityRepository(SlotJpaRepository slotJpaRepository, JPAQueryFactory jpaQueryFactory) {
        super(SlotEntity.class);
        this.slotJpaRepository = slotJpaRepository;
        this.jpaQueryFactory = jpaQueryFactory;
    }


    @Override
    public Slot add(long coachId, WorkoutType workoutType, LocalDateTime eventDatetime, long price) {
        SlotEntity slotEntity = slotJpaRepository.save(SlotEntity.of(coachId, workoutType, eventDatetime, price));

        return slotEntity.toSlot();
    }

    @Override
    public List<SlotSimple> findList(LocalDate date) { // 20240710 형식
        QSlotEntity slot = QSlotEntity.slotEntity;

        LocalDateTime startOfDay = date.atStartOfDay();
        LocalDateTime endOfDay = date.atTime(LocalTime.MAX);

        List<SlotEntity> slots = jpaQueryFactory.selectFrom(slot)
                .where(slot.eventDatetime.between(startOfDay, endOfDay)
                        .and(slot.deletedAt.isNull()))
                .stream().toList();

        return slots.stream().map(SlotEntity::toSlotSimple).toList();
    }

    @Override
    public Slot find(Long slotId) {
        SlotEntity slot = slotJpaRepository.findById(slotId)
                .orElseThrow(() -> new NoDataException(CoreDomainErrorType.NOT_FOUND, "요청 슬롯을 찾지 못했습니다"));
        return slot.toSlot();
    }
}

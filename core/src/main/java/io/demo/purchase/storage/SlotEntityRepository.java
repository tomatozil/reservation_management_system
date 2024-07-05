package io.demo.purchase.storage;

import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import io.demo.purchase.core.domain.error.CoreDomainErrorType;
import io.demo.purchase.core.domain.slot.Slot;
import io.demo.purchase.core.domain.slot.SlotDetail;
import io.demo.purchase.core.domain.slot.SlotRepository;
import io.demo.purchase.support.CustomException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public class SlotEntityRepository extends QuerydslRepositorySupport implements SlotRepository {

    private final SlotJpaRepository slotJpaRepository;
    private final JPAQueryFactory jpaQueryFactory;

    @Autowired
    public SlotEntityRepository(SlotJpaRepository slotJpaRepository, JPAQueryFactory jpaQueryFactory) {
        super(SlotEntity.class);
        this.slotJpaRepository = slotJpaRepository;
        this.jpaQueryFactory = jpaQueryFactory;
    }


    @Override
    public List<Slot> findList(LocalDateTime date) { // 20240710 형식
        QSlotEntity slot = QSlotEntity.slotEntity;

        List<SlotEntity> slots = jpaQueryFactory.selectFrom(slot).where(x.eq(date)).stream().toList();

        return slots.stream().map(SlotEntity::toSlotSimple).toList();
    }

    @Override
    public SlotDetail find(Long slotId) {
        SlotEntity slot = slotJpaRepository.findById(slotId).orElseThrow(() ->
                new CustomException(CoreDomainErrorType.BAD_REQUEST_DATA, "요청 슬롯을 찾지 못했습니다"));
        return slot.toSlotDetail();
    }
}

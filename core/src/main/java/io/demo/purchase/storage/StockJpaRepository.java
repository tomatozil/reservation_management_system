package io.demo.purchase.storage;

import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;

import java.util.Optional;

interface StockJpaRepository extends JpaRepository<StockEntity, Long> {
    @Lock(LockModeType.PESSIMISTIC_READ)
    Optional<StockEntity> findBySlotId(long slotId);
}

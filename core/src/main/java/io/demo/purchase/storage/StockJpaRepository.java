package io.demo.purchase.storage;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StockJpaRepository extends JpaRepository<StockEntity, Long> {
    Optional<StockEntity> findBySlotId(long slotId);
}

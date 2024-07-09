package io.demo.purchase.storage;

import org.springframework.data.jpa.repository.JpaRepository;

public interface StockJpaRepository extends JpaRepository<StockEntity, Long> {
}

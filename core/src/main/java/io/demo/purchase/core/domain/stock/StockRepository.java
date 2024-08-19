package io.demo.purchase.core.domain.stock;

import io.demo.purchase.storage.StockEntity;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StockRepository {
    Optional<Stock> findBySlotId(long slotId);

    StockEntity findById(long stockId);

    void update(Stock newStock);
}


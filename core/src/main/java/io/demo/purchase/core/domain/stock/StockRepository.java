package io.demo.purchase.core.domain.stock;

import org.springframework.stereotype.Repository;

@Repository
public interface StockRepository {
    long findQuantity(long slotId);
}

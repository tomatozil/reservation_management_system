package io.demo.purchase.core.domain.stock;

import io.demo.purchase.core.domain.slot.SlotReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class StockReader {

    private final SlotReader slotReader;
    private final StockRepository stockRepository;

    @Autowired
    public StockReader(SlotReader slotReader, StockRepository stockRepository) {
        this.slotReader = slotReader;
        this.stockRepository = stockRepository;
    }

    public Stock find(long slotId) {
        return stockRepository.findBySlotId(slotId);
    }

    public void getNamedLock() {
        stockRepository.acquireNamedLock();
    }

    public void releaseNamedLock() {
        stockRepository.releaseNamedLock();
    }
}

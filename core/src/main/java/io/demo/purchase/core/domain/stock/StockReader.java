package io.demo.purchase.core.domain.stock;

import io.demo.purchase.core.domain.slot.Slot;
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

    public long findQuantity(long slotId) {
        // 유효한 slot인지 확인
        Slot slot = slotReader.find(slotId);

        return stockRepository.findQuantity(slot.getId());
    }
}

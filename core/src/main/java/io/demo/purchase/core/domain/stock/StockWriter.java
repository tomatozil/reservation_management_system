package io.demo.purchase.core.domain.stock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class StockWriter {

    private final StockRepository stockRepository;

    @Autowired
    public StockWriter(StockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }

    public void updateStock(Stock newStock) {
        stockRepository.update(newStock);
    }
}

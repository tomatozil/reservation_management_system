package io.demo.purchase.core.domain.stock;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Stock {
    long id;
    long slotId;
    long stock;
    long total;

    public Stock(long id, long slotId, long stock, long total) {
        this.id = id;
        this.slotId = slotId;
        this.stock = stock;
        this.total = total;
    }
}

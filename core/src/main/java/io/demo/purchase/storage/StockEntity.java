package io.demo.purchase.storage;

import io.demo.purchase.core.domain.stock.Stock;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "stock")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
class StockEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "slot_id", nullable = false)
    private Long slotId;

    @Builder.Default
    @Column(nullable = false)
    private Long stock = 0L;

    @Column(nullable = false)
    private Long total;

    public StockEntity(long slotId, long total) {
        this.slotId = slotId;
        this.total = total;
    }

    public Stock toStock() {
        return new Stock(id, slotId, stock, total);
    }

    public void updateStock(long newStock) {
        this.stock = newStock;
    }

    public static StockEntity of(long slotId, long quantity) {
        return StockEntity.builder()
                .slotId(slotId)
                .total(quantity)
                .build();
    }
}

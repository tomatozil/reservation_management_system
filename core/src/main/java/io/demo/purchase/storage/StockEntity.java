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
public class StockEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "slot_id", nullable = false)
    private Long slotId;

    @Column(nullable = false)
    private Long stock;

    @Column(nullable = false)
    private Long total;

    public Stock toStock() {
        return new Stock(id, slotId, stock, total);
    }

    public void updateStock(long newStock) {
        this.stock = newStock;
    }
}

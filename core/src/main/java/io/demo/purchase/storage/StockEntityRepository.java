package io.demo.purchase.storage;

import com.querydsl.jpa.impl.JPAQueryFactory;
import io.demo.purchase.support.exception.CoreDomainErrorType;
import io.demo.purchase.core.domain.stock.Stock;
import io.demo.purchase.core.domain.stock.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
class StockEntityRepository extends QuerydslRepositorySupport implements StockRepository {

    private final StockJpaRepository stockJpaRepository;
    private final JPAQueryFactory jpaQueryFactory;

    @Autowired
    public StockEntityRepository(StockJpaRepository stockJpaRepository, JPAQueryFactory jpaQueryFactory) {
        super(StockEntity.class);
        this.stockJpaRepository = stockJpaRepository;
        this.jpaQueryFactory = jpaQueryFactory;
    }

    @Override
    public void add(long slotId, long quantity) {
        stockJpaRepository.save(StockEntity.of(slotId, quantity));
    }

    @Override
    public Optional<Stock> findBySlotId(long slotId) {
                QStockEntity stock = QStockEntity.stockEntity;

        Optional<StockEntity> optStockEntity = Optional.ofNullable(jpaQueryFactory.selectFrom(stock)
                .where(stock.slotId.eq(slotId)
                        .and(stock.deletedAt.isNull()))
                .fetchFirst());
//        Optional<StockEntity> optStockEntity = stockJpaRepository.findBySlotId(slotId);

        return optStockEntity.map(StockEntity::toStock);
    }

    @Override
    public StockEntity findById(long stockId) {
        return stockJpaRepository.findById(stockId)
                .orElseThrow(() -> new NoDataException(CoreDomainErrorType.NOT_FOUND, "해당 재고를 찾을 수 없습니다"));
    }

    @Override
    public void update(Stock newStock) {
        StockEntity stockEntity = this.findById(newStock.getId());

        stockEntity.updateStock(newStock.getStock());
        stockJpaRepository.save(stockEntity);
    }
}

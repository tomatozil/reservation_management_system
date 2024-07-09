package io.demo.purchase.storage;

import com.querydsl.jpa.impl.JPAQueryFactory;
import io.demo.purchase.core.domain.error.CoreDomainErrorType;
import io.demo.purchase.core.domain.stock.StockRepository;
import io.demo.purchase.support.CustomException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

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
    public long findQuantity(long slotId) {
        QStockEntity stockEntity = QStockEntity.stockEntity;

        StockEntity stock = jpaQueryFactory.selectFrom(stockEntity)
                .where(stockEntity.slotId.eq(slotId).and(stockEntity.deletedAt.isNull()))
                .fetchFirst();
        if (stock == null) {
            throw new CustomException(CoreDomainErrorType.BAD_REQUEST_DATA, "요청 슬롯 수량 정보를 찾지 못했습니다");
        }

        return stock.getTotal();
    }
}

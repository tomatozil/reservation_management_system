package io.demo.purchase.storage;

import com.querydsl.jpa.impl.JPAQueryFactory;
import io.demo.purchase.core.AlertUserRetryException;
import io.demo.purchase.support.exception.CoreDomainErrorType;
import io.demo.purchase.core.domain.stock.Stock;
import io.demo.purchase.core.domain.stock.StockRepository;
import jakarta.persistence.LockModeType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
class StockEntityRepository extends QuerydslRepositorySupport implements StockRepository {

    private final StockJpaRepository stockJpaRepository;
    private final StockNamedLockJpaRepository stockNamedLockJpaRepository;
    private final JPAQueryFactory jpaQueryFactory;

    @Autowired
    public StockEntityRepository(StockJpaRepository stockJpaRepository,
                                 StockNamedLockJpaRepository stockNamedLockJpaRepository,
                                 JPAQueryFactory jpaQueryFactory) {
        super(StockEntity.class);
        this.stockJpaRepository = stockJpaRepository;
        this.stockNamedLockJpaRepository = stockNamedLockJpaRepository;
        this.jpaQueryFactory = jpaQueryFactory;
    }

    @Override
    public void add(long slotId, long quantity) {
        stockJpaRepository.save(StockEntity.of(slotId, quantity));
    }

    @Override
    public Stock findBySlotId(long slotId) {
                QStockEntity stock = QStockEntity.stockEntity;

        StockEntity optStockEntity = Optional.ofNullable(jpaQueryFactory.selectFrom(stock)
                .where(stock.slotId.eq(slotId)
                        .and(stock.deletedAt.isNull()))
//                        .setLockMode(LockModeType.PESSIMISTIC_WRITE)
                .fetchOne())
                .orElseThrow(() -> new NoDataException("존재하지 않는 슬롯 재고입니다"));

//        Optional<StockEntity> optStockEntity = stockJpaRepository.findBySlotId(slotId);

        return optStockEntity.toStock();
    }

    @Override
    public void increaseStock(long stockId) {
        StockEntity stockEntity = stockJpaRepository.findById(stockId)
                .orElseThrow(() -> new NoDataException("해당 재고를 찾을 수 없습니다"));

        if (stockEntity.getStock() >= stockEntity.getTotal()) {
            throw new AlertUserRetryException(CoreDomainErrorType.REQUEST_FAILED, "인원 초과로 예약이 불가능합니다");
        }
        stockEntity.updateStock(stockEntity.getStock()+1);
        stockJpaRepository.save(stockEntity);
    }

    public void acquireNamedLock() {
        Integer result = stockNamedLockJpaRepository.getNamedLock();
        if (result == null || result == 0) {
            // 재시도 로직
        }
    }

    public void releaseNamedLock() {
       Integer result = stockNamedLockJpaRepository.releaseNamedLock();
        if (result == null || result == 0) {
            // 예외 던지기 등의 적절한 조치 취하기
        }
    }
}

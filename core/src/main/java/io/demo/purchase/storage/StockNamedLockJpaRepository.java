package io.demo.purchase.storage;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

interface StockNamedLockJpaRepository extends JpaRepository<StockEntity, Long> {
    @Query(value = "SELECT GET_LOCK('stock.str',3000)", nativeQuery = true)
    Integer getNamedLock();

    @Query(value = "SELECT RELEASE_LOCK('stock.str')", nativeQuery = true)
    Integer releaseNamedLock();
}

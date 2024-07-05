package io.demo.purchase.storage;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

public interface SlotJpaRepository extends JpaRepository<SlotEntity, Long> {
}

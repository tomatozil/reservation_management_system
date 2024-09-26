package io.demo.purchase.storage;

import org.springframework.data.jpa.repository.JpaRepository;

interface SlotJpaRepository extends JpaRepository<SlotEntity, Long> {
}

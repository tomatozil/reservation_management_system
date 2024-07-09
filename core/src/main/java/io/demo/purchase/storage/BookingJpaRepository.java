package io.demo.purchase.storage;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingJpaRepository extends JpaRepository<BookingEntity, Long> {
}

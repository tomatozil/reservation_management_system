package io.demo.purchase.storage;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

interface BookingJpaRepository extends JpaRepository<BookingEntity, Long> {

    // test
    @Modifying
    @Transactional
    @Query("delete from BookingEntity b where b.id = :bookingId")
    int deleteByIdQuery(@Param("bookingId") Long bookingId);

    // test
    List<BookingEntity> findBySlotId(long slotId);
}

package io.demo.purchase.storage;

import io.demo.purchase.core.domain.booking.Booking;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "booking", indexes = @Index(name = "idx_user_slot", columnList = "user_id, slot_id"))
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BookingEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "slot_id", nullable = false)
    private Long slotId;

    public BookingEntity(long userId, long slotId) {
        this.userId = userId;
        this.slotId = slotId;
    }

    public Booking toBooking() {
        return new Booking(id, userId, slotId);
    }

    public static BookingEntity of(long userId, long slotId) {
        return new BookingEntity(userId, slotId);
    }
}

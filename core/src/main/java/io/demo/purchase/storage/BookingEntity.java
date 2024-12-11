package io.demo.purchase.storage;

import io.demo.purchase.core.domain.booking.Booking;
import io.demo.purchase.support.BookingStatus;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.repository.Lock;

@Entity
@Table(name = "booking", indexes = @Index(name = "idx_user_slot", columnList = "user_id, slot_id"))
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
class BookingEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "slot_id", nullable = false)
    private Long slotId;

    @Builder.Default
    @Enumerated(EnumType.STRING)
    private BookingStatus status = BookingStatus.CONFIRMED;

    public BookingEntity(long userId, long slotId) {
        this.userId = userId;
        this.slotId = slotId;
    }

    public Booking toBooking() {
        return new Booking(id, userId, slotId);
    }

    public void updateStatus(BookingStatus to) {
        this.status = to;
    }

    public static BookingEntity of(long userId, long slotId) {
        return new BookingEntity(userId, slotId);
    }
}

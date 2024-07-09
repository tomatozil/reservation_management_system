package io.demo.purchase.core.domain.booking;


public class Booking {
    long id;
    long userId;
    long slotId;

    public Booking(long id, long userId, long slotId) {
        this.id = id;
        this.userId = userId;
        this.slotId = slotId;
    }
}

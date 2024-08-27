package io.demo.purchase.admin;

import lombok.Getter;

@Getter
public class AppendSlotResponse {
    long slotId;

    public AppendSlotResponse(long slotId) {
        this.slotId = slotId;
    }
}

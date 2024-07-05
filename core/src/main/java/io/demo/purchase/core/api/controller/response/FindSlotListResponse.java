package io.demo.purchase.core.api.controller.response;

import io.demo.purchase.core.domain.slot.Slot;
import lombok.Getter;

import java.util.List;

@Getter
public class FindSlotListResponse {
    List<Slot> slots;

    public FindSlotListResponse(List<Slot> slots) {
        this.slots = slots;
    }
}

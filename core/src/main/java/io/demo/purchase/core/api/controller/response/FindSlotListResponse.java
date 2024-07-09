package io.demo.purchase.core.api.controller.response;

import io.demo.purchase.core.domain.slot.SlotSimple;
import lombok.Getter;

import java.util.List;

@Getter
public class FindSlotListResponse {
    List<SlotSimple> slots;

    public FindSlotListResponse(List<SlotSimple> slots) {
        this.slots = slots;
    }
}

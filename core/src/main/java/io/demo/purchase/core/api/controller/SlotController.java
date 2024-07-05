package io.demo.purchase.core.api.controller;

import io.demo.purchase.core.api.controller.response.FindSlotListResponse;
import io.demo.purchase.core.api.controller.response.FindSlotResponse;
import io.demo.purchase.core.domain.slot.Slot;
import io.demo.purchase.core.domain.slot.SlotDetail;
import io.demo.purchase.core.domain.slot.SlotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@RestController
public class SlotController {

    private final SlotService slotService;

    @Autowired
    public SlotController(SlotService slotService) {
        this.slotService = slotService;
    }


    @GetMapping("/slots/{date}")
    public FindSlotListResponse findList(@PathVariable("date") @DateTimeFormat(pattern = "yyyyMMdd") LocalDateTime date) {
        List<Slot> slots = slotService.findList(date);
        return new FindSlotListResponse(slots);
    }

    @GetMapping("/slots/{slotId}")
    public FindSlotResponse find(@PathVariable("slotId") Long slotId) {
        SlotDetail slot = slotService.find(slotId);
        return slot.toResponse();
    }
}

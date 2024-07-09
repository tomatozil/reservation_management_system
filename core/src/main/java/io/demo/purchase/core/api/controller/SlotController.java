package io.demo.purchase.core.api.controller;

import io.demo.purchase.core.api.controller.response.FindSlotListResponse;
import io.demo.purchase.core.api.controller.response.FindSlotResponse;
import io.demo.purchase.core.domain.slot.SlotSimple;
import io.demo.purchase.core.domain.slot.Slot;
import io.demo.purchase.core.domain.slot.SlotService;
import io.demo.purchase.core.domain.user.User;
import io.demo.purchase.support.argumentresolver.AuthorizedUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
public class SlotController {

    private final SlotService slotService;

    @Autowired
    public SlotController(SlotService slotService) {
        this.slotService = slotService;
    }


    @GetMapping("/slots/{date}")
    public FindSlotListResponse findList(
            @AuthorizedUser User user,
            @PathVariable("date") @DateTimeFormat(pattern = "yyyyMMdd") LocalDate date
    ) {
        List<SlotSimple> slots = slotService.findList(date);
        return new FindSlotListResponse(slots);
    }

    @GetMapping("/slots/{slotId}")
    public FindSlotResponse find(@AuthorizedUser User user, @PathVariable("slotId") Long slotId) {
        Slot slot = slotService.find(slotId);
        return slot.toResponse();
    }
}

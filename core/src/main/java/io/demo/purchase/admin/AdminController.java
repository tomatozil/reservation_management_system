package io.demo.purchase.admin;

import io.demo.purchase.core.domain.slot.SlotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdminController {
    private final SlotService slotService;

    @Autowired
    public AdminController(SlotService slotService) {
        this.slotService = slotService;
    }

    @PostMapping("/admin/slot")
    public AppendSlotResponse addSlot(@RequestBody AppendSlotRequest appendSlotRequest) {
        long slotId = slotService.add(appendSlotRequest.toAddSlot());

        return new AppendSlotResponse(slotId);
    }

}

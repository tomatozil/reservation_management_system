package io.demo.purchase.core.api.controller;

import io.demo.purchase.core.domain.slot.SlotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
public class SlotController {

    private final SlotService slotService;

    @Autowired
    public SlotController(SlotService slotService) {
        this.slotService = slotService;
    }


    @GetMapping("/slots/{date}")
    public void findList(@PathVariable("date") @DateTimeFormat(pattern = "yyyyMMdd") LocalDateTime date) {
        slotService.findList(date);
    }
}

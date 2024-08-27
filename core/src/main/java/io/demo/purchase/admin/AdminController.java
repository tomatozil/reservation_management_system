package io.demo.purchase.admin;

import io.demo.purchase.core.domain.slot.SlotService;
import io.demo.purchase.core.domain.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class AdminController {

    private final UserService userService;
    private final SlotService slotService;

    @Autowired
    public AdminController(UserService userService, SlotService slotService) {
        this.userService = userService;
        this.slotService = slotService;
    }

    @PutMapping("/admin/role/{userId}")
    public void updateUserRole(@PathVariable("userId") Long userId, @RequestBody UpdateUserRoleRequest updateUserRoleRequest) {
        userService.updateUserRole(userId, updateUserRoleRequest.getTo());
    }

    @PostMapping("/admin/slot")
    public AppendSlotResponse addSlot(@RequestBody AppendSlotRequest appendSlotRequest) {
        long slotId = slotService.add(appendSlotRequest.toAddSlot());

        return new AppendSlotResponse(slotId);
    }
}

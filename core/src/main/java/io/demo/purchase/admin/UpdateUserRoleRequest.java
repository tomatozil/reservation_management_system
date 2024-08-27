package io.demo.purchase.admin;

import io.demo.purchase.support.RoleType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateUserRoleRequest {
    RoleType to;

    public UpdateUserRoleRequest(RoleType to) {
        this.to = to;
    }
}

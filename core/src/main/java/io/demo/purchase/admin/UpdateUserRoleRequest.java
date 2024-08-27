package io.demo.purchase.admin;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.demo.purchase.support.RoleType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateUserRoleRequest {
    RoleType to;

    public UpdateUserRoleRequest(@JsonProperty(value = "to")RoleType to) {
        this.to = to;
    }
}

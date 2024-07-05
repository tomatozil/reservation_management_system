package io.demo.purchase.core.api.controller.response;

import lombok.Getter;

@Getter
public class AppendUserResponse {
    Long userId;

    public AppendUserResponse(long userId) {
        this.userId = userId;
    }
}

package io.demo.purchase.core.api.controller.response;

import lombok.Getter;

//@Getter
public class UserTokenInfo {
    long userId;
    String accessToken;

    public UserTokenInfo(long userId, String accessToken) {
        this.userId = userId;
        this.accessToken = accessToken;
    }

    public AppendUserResponse toResponse(long userId) {
        return new AppendUserResponse(userId);
    }

    public long getUserId() {
        return userId;
    }

    public String getAccessToken() {
        return accessToken;
    }
}

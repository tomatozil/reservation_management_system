package io.demo.purchase.core.api.controller.response;

public class UserTokenInfo {
    long userId;
    String accessToken;

    public UserTokenInfo(long userId, String accessToken) {
        this.userId = userId;
        this.accessToken = accessToken;
    }

    public long getUserId() {
        return userId;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public AppendUserResponse toResponse() {
        return new AppendUserResponse(userId);
    }
}

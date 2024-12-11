package io.demo.purchase.support;

public enum RoleType {
    USER("사용자"),
    ADMIN("관리자");

    private String role;

    RoleType(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}

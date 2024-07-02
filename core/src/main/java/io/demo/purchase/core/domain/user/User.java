package io.demo.purchase.core.domain.user;

public class User {
    long userId;
    String name;
    String email;
    private String password;

    public User(Long id, String name, String email, String password) {
        this.userId = id;
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public String getPassword() {
        return password;
    }
}

package io.demo.purchase.storage;

import io.demo.purchase.core.domain.user.User;
import jakarta.persistence.*;

@Entity
class UserEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(length = 10, nullable = false)
    private String name;

    private String email;

    @Column(nullable = false)
    private String password;

    private String address;

    public UserEntity() {}

    public UserEntity(String name, String email, String password) {
        super();
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public long getId() {
        return id;
    }


    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    User toUser() {
        return new User(id, name, email, password);
    }
}

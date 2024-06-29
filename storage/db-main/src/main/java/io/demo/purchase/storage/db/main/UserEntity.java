package io.demo.purchase.storage.db.main;

import jakarta.persistence.*;

@Entity
class UserEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

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

    public Integer getId() {
        return id;
    }
}

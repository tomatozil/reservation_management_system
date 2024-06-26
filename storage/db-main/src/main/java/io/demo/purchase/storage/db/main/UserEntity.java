package io.demo.purchase.storage.db.main;

import jakarta.persistence.*;

@Entity
public class UserEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(length = 10, nullable = false)
    private String name;

    @Column(nullable = false)
    private String password;

    private String address;
}

package io.demo.purchase.storage;

import io.demo.purchase.core.domain.user.User;
import io.demo.purchase.support.RoleType;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "user", indexes = @Index(name = "idx_email_name", columnList = "email, name"))
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Builder.Default
    @Column(name="role_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private RoleType roleType = RoleType.USER;

    @Column(length = 10, nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    private String address;

    public UserEntity(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    void updateRole(RoleType to) {
        this.roleType = to;
    }

    User toUser() {
        return new User(id, roleType, name, email, password);
    }
}

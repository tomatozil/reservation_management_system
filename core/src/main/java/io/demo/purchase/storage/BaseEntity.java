package io.demo.purchase.storage;


import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass
@Getter
abstract class BaseEntity {

    @Column(name = "created_at", nullable = false, columnDefinition = "TIMESTAMP")
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(name = "updated_at", columnDefinition = "TIMESTAMP")
    @LastModifiedDate
    private LocalDateTime updateAt;

    @Column(name = "deleted_at", columnDefinition = "TIMESTAMP")
    private LocalDateTime deletedAt;

    public void setDeletedAt() {
        this.deletedAt = LocalDateTime.now();
    }
}

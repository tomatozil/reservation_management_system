package io.demo.purchase.storage.db.main;

import org.springframework.data.jpa.repository.JpaRepository;

interface UserJpaRepository extends JpaRepository<UserEntity, Integer> {
}

package io.demo.purchase.storage;

import org.springframework.data.jpa.repository.JpaRepository;


interface UserJpaRepository extends JpaRepository<UserEntity, Long> {
    // TODO: queryDsl로 바꾸기
    UserEntity findUserEntityByEmailAndPassword(String email, String password);
}

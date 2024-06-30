package io.demo.purchase.storage;

import io.demo.purchase.core.domain.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
class UserEntityRepository implements UserRepository {

    private final UserJpaRepository userJpaRepository;

    @Autowired
    public UserEntityRepository(UserJpaRepository userJpaRepository) {
        this.userJpaRepository = userJpaRepository;
    }

    @Override
    public Integer add(String name, String email, String password) {
        UserEntity user = userJpaRepository.save(new UserEntity(name, email, password));
        return user.getId();
    }
}

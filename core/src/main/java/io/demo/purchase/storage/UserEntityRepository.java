package io.demo.purchase.storage;

import io.demo.purchase.core.domain.error.CoreDomainErrorType;
import io.demo.purchase.core.domain.user.User;
import io.demo.purchase.core.domain.user.UserRepository;
import io.demo.purchase.support.CustomException;
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
    public long add(String name, String email, String password) {
        UserEntity user = userJpaRepository.save(new UserEntity(name, email, password));
        return user.getId();
    }

    @Override
    public User find(long userId) {
        UserEntity user = userJpaRepository.findById(userId).orElseThrow(() ->
                        new CustomException(CoreDomainErrorType.UNAUTHORIZED, "유저를 찾지 못했습니다"));
        return user.toUser();
    }

//    @Override
//    public User find(String email, String password) {
//        UserEntity user = userJpaRepository.findUserEntityByEmailAndPassword(email, password);
//        return user.toUser();
//    }
}

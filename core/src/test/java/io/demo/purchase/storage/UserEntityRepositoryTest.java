package io.demo.purchase.storage;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;


import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserEntityRepositoryTest {

//    @Autowired
//    private UserEntityRepository userEntityRepository;
//
//    @PersistenceContext
//    private TestEntityManager entityManager;

//    TODO: transactional 없이 하는 테스트
//    @BeforeEach
//    public void setUp() {
//
//    }
//
//    @AfterEach
//    public void tearDown() {
//
//    }

    @Test
    @DisplayName("유저 추가하고 조회시 잘 되는지 확인")
    @Transactional
    void 유저_추가() {
        // Given
//        String name = "jiyun";
//        String email = "email@email.com";
//        String password = "encryptedPassword";
//
//        // When
//        long userId = userEntityRepository.add(name, email, password);
//
//        // Then
//        UserEntity retrievedUser = entityManager.find(UserEntity.class, userId);
//        assertThat(retrievedUser).isNotNull();
//        assertThat(retrievedUser.getName()).isEqualTo(name);
//        assertThat(retrievedUser.getEmail()).isEqualTo(email);
//
//        entityManager.remove(retrievedUser);
//        entityManager.flush();
    }
}
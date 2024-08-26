package io.demo.purchase.core.domain.user;


import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
class UserWriterTest {

    @Autowired
    UserWriter userWriter;
    @Autowired
    private UserReader userReader;

//    UserRepository userRepositoryMock = new UserRepository() {
//        @Override
//        public long add(String name, String email, String password) {
//            return 1L;
//        }
//
//        @Override
//        public User find(long userId) {
//            return null;
//        }
//
//    };

    @Test
    @DisplayName("유저 추가시 id와 토큰을 담은 UserTokenInfo 객체가 잘 만들어지는지 확인")
    @Transactional
    void appendTest() {

        long userId = userWriter.append("jiyun", "email@email.com", "password");

        User user = userReader.findById(userId);

        assertThat(user.id).isEqualTo(userId);
    }
}
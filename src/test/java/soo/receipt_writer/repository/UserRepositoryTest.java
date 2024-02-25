package soo.receipt_writer.repository;

import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import soo.receipt_writer.domain.User;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;

    @Test
    public void insertOne() {
        User user1 = User.addPasskeyUser("asd", "user", "pppppppp");
        int insert = userRepository.insertOne(user1);
        System.out.println("insert = " + insert);
    }

    @Test
    public void selectTest() {
        User user = userRepository.selectOneById("user");
        System.out.println("user = " + user);
    }

}
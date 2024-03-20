package soo.receipt_writer.users.repository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;
import soo.receipt_writer.users.repository.dao.RegisterUserDAO;

@Slf4j
@Repository
@RequiredArgsConstructor
public class UserRepository {

    private final SqlSession session;

    public int insertOne(RegisterUserDAO user) {
        return session.insert("TB_USER.insertOne", user);
    }

    public User selectOneById(String userId) {
        return session.selectOne("TB_USER.selectOneById", userId);
    }
}

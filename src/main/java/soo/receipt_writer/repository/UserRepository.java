package soo.receipt_writer.repository;

import lombok.RequiredArgsConstructor;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;
import soo.receipt_writer.domain.User;

@Repository
@RequiredArgsConstructor
public class UserRepository {

    private final SqlSession session;

    public int insertOne(User user) {
        return session.insert("TB_USER.insertOne", user);
    }

    public User selectOneById(String userId) {
        return session.selectOne("TB_USER.selectOneById", userId);
    }
}
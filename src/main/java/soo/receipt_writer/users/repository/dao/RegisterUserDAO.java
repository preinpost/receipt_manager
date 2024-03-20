package soo.receipt_writer.users.repository.dao;

public record RegisterUserDAO(
        String uid,
        String userId,
        String passkey,
        String joinDate
) {
}

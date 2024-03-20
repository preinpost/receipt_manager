package soo.receipt_writer.users.repository;

import jakarta.annotation.Nullable;

public record User(
        String uid,
        String userId,
        @Nullable
        String password,
        @Nullable
        String passkey,
        String joinDate,
        String delYn
) {
}

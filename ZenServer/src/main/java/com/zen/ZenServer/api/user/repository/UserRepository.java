package com.zen.ZenServer.api.user.repository;

import com.zen.ZenServer.api.user.domain.User;
import com.zen.ZenServer.global.exception.CustomException;
import com.zen.ZenServer.global.response.enums.ErrorCode;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String username);

    default User findByIdByOrThrow(Long id) {
        return findById(id)
                .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));
    }
}

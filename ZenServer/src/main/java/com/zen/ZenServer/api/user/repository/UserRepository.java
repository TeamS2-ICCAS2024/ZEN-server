package com.zen.ZenServer.api.user.repository;

import com.zen.ZenServer.api.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long>{
}

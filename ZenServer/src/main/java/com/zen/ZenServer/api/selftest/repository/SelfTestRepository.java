package com.zen.ZenServer.api.selftest.repository;

import com.zen.ZenServer.api.selftest.domain.SelfTest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SelfTestRepository extends JpaRepository<SelfTest, Long>{
    List<SelfTest> findByUserId(Long userId);
}

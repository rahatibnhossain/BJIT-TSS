package com.bjit.tss.repository;

import com.bjit.tss.entity.LoginInfo;
import com.bjit.tss.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface UserRepository extends JpaRepository<UserInfo, Long> {
    Optional<UserInfo> findByEmail(String email);
}
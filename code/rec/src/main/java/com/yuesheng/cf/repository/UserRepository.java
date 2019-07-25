package com.yuesheng.cf.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.yuesheng.cf.entity.User;

public interface UserRepository extends JpaRepository<User, String> {
    public User findByUsername(String userName);
}

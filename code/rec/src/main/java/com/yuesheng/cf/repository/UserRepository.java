package com.yuesheng.cf.repository;

import com.yuesheng.cf.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
    public User findByUsername(String userName);
}

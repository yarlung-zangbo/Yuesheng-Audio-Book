package com.yuesheng.cf.dao;

import com.yuesheng.cf.entity.User;

import java.util.List;

public interface UserDao {
    /* Test */
    public List<User> findAllUser();

    /* Test */
    public User findByUsername(String userName);
}

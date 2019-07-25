package com.yuesheng.cf.daoimpl;

import com.yuesheng.cf.dao.UserDao;
import com.yuesheng.cf.entity.User;
import com.yuesheng.cf.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;



import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    @Autowired
    private UserRepository userRepository;

    /* Test */
    @Override
    public List<User> findAllUser() {
        return userRepository.findAll();
    }
    /* Test */

    @Override
    public User findByUsername(String userName) {
        return userRepository.getOne(userName);
    }
}

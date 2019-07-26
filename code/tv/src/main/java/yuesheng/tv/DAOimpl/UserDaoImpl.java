package yuesheng.tv.DAOimpl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import yuesheng.tv.DAO.UserDao;
import yuesheng.tv.Entity.User;
import yuesheng.tv.Repository.UserRepository;

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
    public User save(User user) {
        return userRepository.saveAndFlush(user);
    }

    @Override
    public User findByUsername(String userName) {
        return userRepository.getOne(userName);
    }
}

package vt.vtservice.DAOimpl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import vt.vtservice.DAO.UserDao;
import vt.vtservice.Entity.User;
import vt.vtservice.Repository.UserRepository;


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

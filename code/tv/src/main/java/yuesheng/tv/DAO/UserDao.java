package yuesheng.tv.DAO;

import yuesheng.tv.Entity.User;

import java.util.List;

public interface UserDao {
    /* Test */
    public List<User> findAllUser();
    /* Test */

    public User save(User user);
    public User findByUsername(String userName);
}

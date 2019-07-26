package yuesheng.tv.Repository;


import org.springframework.data.jpa.repository.JpaRepository;
import yuesheng.tv.Entity.User;

public interface UserRepository  extends JpaRepository<User, String> {
    public User findByUserName(String userName);
}

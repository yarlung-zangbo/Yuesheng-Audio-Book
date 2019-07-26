package vt.vtservice.Repository;


import org.springframework.data.jpa.repository.JpaRepository;
import vt.vtservice.Entity.User;


public interface UserRepository  extends JpaRepository<User, String> {
    public User findByUserName(String userName);
}

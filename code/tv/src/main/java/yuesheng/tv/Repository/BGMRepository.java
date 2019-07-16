package yuesheng.tv.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import yuesheng.tv.Entity.BGM;
import yuesheng.tv.Entity.EntityKey.BGMKey;

import java.util.List;

public interface BGMRepository extends JpaRepository<BGM,BGMKey> {
    public List<BGM> findByBGMKeyname(String name);
}

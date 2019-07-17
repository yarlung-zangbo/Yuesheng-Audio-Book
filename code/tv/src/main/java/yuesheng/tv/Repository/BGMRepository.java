package yuesheng.tv.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import yuesheng.tv.Entity.BGM;
import yuesheng.tv.Entity.EntityKey.BGMKey;

import java.util.List;
@Repository
public interface BGMRepository extends JpaRepository<BGM,BGMKey> {
    public List<BGM> findByKeyName(String name);
}

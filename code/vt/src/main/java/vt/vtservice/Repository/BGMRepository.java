package vt.vtservice.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vt.vtservice.Entity.BGM;
import vt.vtservice.Entity.EntityKey.BGMKey;

import java.util.List;

@Repository
public interface BGMRepository extends JpaRepository<BGM, BGMKey> {
    public List<BGM> findByKeyName(String name);
}

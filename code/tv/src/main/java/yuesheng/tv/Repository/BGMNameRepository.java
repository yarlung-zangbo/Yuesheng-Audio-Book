package yuesheng.tv.Repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import yuesheng.tv.Entity.BGMName;

@Repository
public interface BGMNameRepository extends JpaRepository<BGMName,String> {
}

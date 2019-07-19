package vt.vtservice.Repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vt.vtservice.Entity.BGMName;


@Repository
public interface BGMNameRepository extends JpaRepository<BGMName,String> {
}

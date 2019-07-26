package vt.vtservice.Repository;


import org.springframework.data.jpa.repository.JpaRepository;
import vt.vtservice.Entity.Soundbook;


import java.util.List;

public interface SoundbookRepository extends JpaRepository<Soundbook, Integer> {
    public Soundbook deleteByBookId(int bookId);
    public List<Soundbook> findByNameContaining(String name);
    public List<Soundbook> findByRealeaseLessThanAndNameContaining(String time, String name);
}

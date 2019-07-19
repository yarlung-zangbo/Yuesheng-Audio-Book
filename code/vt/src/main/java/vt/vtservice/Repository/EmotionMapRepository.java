package vt.vtservice.Repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vt.vtservice.Entity.EmotionMap;
import vt.vtservice.Entity.EntityKey.EmotionMapKey;

@Repository
public interface EmotionMapRepository extends JpaRepository<EmotionMap, EmotionMapKey> {
    EmotionMap findByEmotionMapKey(EmotionMapKey EMK);
}

package yuesheng.tv.Repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import yuesheng.tv.Entity.EmotionMap;
import yuesheng.tv.Entity.EntityKey.EmotionMapKey;
@Repository
public interface EmotionMapRepository extends JpaRepository<EmotionMap, EmotionMapKey> {
    EmotionMap findByEmotionMapKey(EmotionMapKey EMK);
}

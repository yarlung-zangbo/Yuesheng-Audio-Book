package yuesheng.tv.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import yuesheng.tv.Entity.Sound;

public interface SoundRepository extends MongoRepository<Sound, String> {
    public Sound findByName(String name);
}

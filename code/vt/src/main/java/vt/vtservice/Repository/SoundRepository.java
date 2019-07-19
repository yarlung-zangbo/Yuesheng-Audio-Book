package vt.vtservice.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import vt.vtservice.Entity.Sound;


public interface SoundRepository extends MongoRepository<Sound, String> {
    public Sound findByName(String name);
}

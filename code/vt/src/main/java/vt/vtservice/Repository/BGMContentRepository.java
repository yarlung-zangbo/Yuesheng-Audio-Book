package vt.vtservice.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import vt.vtservice.Entity.BGMContent;

public interface BGMContentRepository extends MongoRepository<BGMContent,String> {
    public BGMContent findByName(String name);
}

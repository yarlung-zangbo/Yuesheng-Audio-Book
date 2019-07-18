package yuesheng.tv.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import yuesheng.tv.Entity.BGMContent;

public interface BGMContentRepository extends MongoRepository<BGMContent,String> {
    public BGMContent findByName(String name);
}

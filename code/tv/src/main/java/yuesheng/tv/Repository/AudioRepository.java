package yuesheng.tv.Repository;


import org.springframework.data.mongodb.repository.MongoRepository;
import yuesheng.tv.Entity.Audio;

public interface AudioRepository extends MongoRepository<Audio, String> {
    public Audio findByBookId(int bookId);
    public Audio deleteByBookId(int bookId);
}

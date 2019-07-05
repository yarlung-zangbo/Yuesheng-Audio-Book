package yuesheng.tv.Repository;


import org.springframework.data.mongodb.repository.MongoRepository;
import yuesheng.tv.Entity.TextAudio;

public interface TextAudioRepository extends MongoRepository<TextAudio, String> {
    public TextAudio findByBookId(int bookId);
    public TextAudio deleteByBookId(int bookId);
}

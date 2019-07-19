package vt.vtservice.Repository;


import org.springframework.data.mongodb.repository.MongoRepository;
import vt.vtservice.Entity.TextAudio;


public interface TextAudioRepository extends MongoRepository<TextAudio, String> {
    public TextAudio findByBookId(int bookId);
    public TextAudio deleteByBookId(int bookId);
}

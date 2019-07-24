package yuesheng.tv.DAOimpl;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import yuesheng.tv.DAO.AudioDao;
import yuesheng.tv.Entity.Audio;
import yuesheng.tv.Repository.AudioRepository;

@Repository
public class AudioImpl implements AudioDao {

    @Autowired
    private AudioRepository audioRepository;

    @Override
    public Audio save(Audio textAudio) {
        return audioRepository.insert(textAudio);
    }

    @Override
    public Audio findByBookId(int bookId) {
        return audioRepository.findByBookId(bookId);
    }

    @Override
    public Audio deleteByBookId(int bookId) {
        return audioRepository.deleteByBookId(bookId);
    }
}

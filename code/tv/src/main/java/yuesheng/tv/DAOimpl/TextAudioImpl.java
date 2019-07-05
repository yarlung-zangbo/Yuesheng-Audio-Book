package yuesheng.tv.DAOimpl;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import yuesheng.tv.DAO.TextAudioDao;
import yuesheng.tv.Entity.TextAudio;
import yuesheng.tv.Repository.TextAudioRepository;

@Repository
public class TextAudioImpl implements TextAudioDao {

    @Autowired
    private TextAudioRepository textAudioRepository;

    @Override
    public TextAudio save(TextAudio textAudio) {
        return textAudioRepository.insert(textAudio);
    }

    @Override
    public TextAudio findByBookId(int bookId) {
        return textAudioRepository.findByBookId(bookId);
    }

    @Override
    public TextAudio deleteByBookId(int bookId) {
        return textAudioRepository.deleteByBookId(bookId);
    }
}

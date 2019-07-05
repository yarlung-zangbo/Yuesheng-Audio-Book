package yuesheng.tv.DAO;


import yuesheng.tv.Entity.TextAudio;

public interface TextAudioDao {
    public TextAudio save(TextAudio textAndAudio);
    public TextAudio findByBookId(int bookId);
    public TextAudio deleteByBookId(int bookId);
}


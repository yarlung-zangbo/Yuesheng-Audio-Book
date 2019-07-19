package vt.vtservice.DAO;


import vt.vtservice.Entity.TextAudio;

public interface TextAudioDao {
    public TextAudio save(TextAudio textAndAudio);
    public TextAudio findByBookId(int bookId);
    public TextAudio deleteByBookId(int bookId);
}


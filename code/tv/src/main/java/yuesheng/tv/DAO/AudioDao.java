package yuesheng.tv.DAO;


import yuesheng.tv.Entity.Audio;

public interface AudioDao {
    public Audio save(Audio textAndAudio);
    public Audio findByBookId(int bookId);
    public Audio deleteByBookId(int bookId);
}


package yuesheng.tv.DAO;

import yuesheng.tv.Entity.Sound;

public interface SoundDao {
    public Sound findByName(String name);
    public void insertSound(Sound sound);
}

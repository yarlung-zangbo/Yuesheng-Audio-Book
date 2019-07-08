package yuesheng.tv.Service;


import yuesheng.tv.Entity.Sound;

public interface SoundService {
    public Sound findSound(String name);
    /*
    public void initSound() throws IOException;
    */
    public void saveSound(String name, byte[] content);
}

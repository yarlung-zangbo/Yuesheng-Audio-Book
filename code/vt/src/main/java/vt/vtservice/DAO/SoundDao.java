package vt.vtservice.DAO;


import vt.vtservice.Entity.Sound;

public interface SoundDao {
    public Sound findByName(String name);
    public void insertSound(Sound sounc);
}

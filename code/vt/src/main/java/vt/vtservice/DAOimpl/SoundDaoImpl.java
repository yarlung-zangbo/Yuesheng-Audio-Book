package vt.vtservice.DAOimpl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import vt.vtservice.DAO.SoundDao;
import vt.vtservice.Entity.Sound;
import vt.vtservice.Repository.SoundRepository;


@Repository
public class SoundDaoImpl implements SoundDao {

    @Autowired
    private SoundRepository soundRepository;

    @Override
    public Sound findByName(String name) {
        return soundRepository.findByName(name);
    }

    @Override
    public void insertSound(Sound sound) {
        soundRepository.insert(sound);
    }
}

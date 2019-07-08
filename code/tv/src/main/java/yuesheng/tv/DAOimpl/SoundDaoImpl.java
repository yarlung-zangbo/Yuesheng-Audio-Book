package yuesheng.tv.DAOimpl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import yuesheng.tv.DAO.SoundDao;
import yuesheng.tv.Entity.Sound;
import yuesheng.tv.Repository.SoundRepository;


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

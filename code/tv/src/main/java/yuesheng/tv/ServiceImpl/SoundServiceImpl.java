package yuesheng.tv.ServiceImpl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import yuesheng.tv.DAO.SoundDao;
import yuesheng.tv.Entity.Sound;
import yuesheng.tv.Service.SoundService;

@Service
public class SoundServiceImpl implements SoundService {

    @Autowired
    private SoundDao soundDao;

    @Override
    public Sound findSound(String name) {
        return soundDao.findByName(name);
    }
/*
    @Override
    public void initSound() throws IOException {
        String url="D:/gitRepo/se/db-impl/yuesheng/src/main/resources/static/sound/";
        String[] name={"风铃", "钟声"};
        for(int i=0;i<2;i++){
            String urli=url+i+".mp3";
            InputStream inStream=new FileInputStream(urli);
            byte[] contents=inStream.readAllBytes();
            System.out.println(new String(contents));
            Sound sound=new Sound();
            sound.setName(name[i]);
            sound.setContent(contents);
            soundDao.insertSound(sound);
        }
    }
*/
    @Override
    public void saveSound(String name, byte[] content) {
        Sound sound=new Sound();
        sound.setContent(content);
        sound.setName(name);
        soundDao.insertSound(sound);
    }
}

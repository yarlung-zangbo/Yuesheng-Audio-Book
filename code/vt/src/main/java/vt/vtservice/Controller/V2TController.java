package vt.vtservice.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import vt.vtservice.DAO.SoundbookDao;
import vt.vtservice.DAO.UserDao;
import vt.vtservice.Entity.Aduio;
import vt.vtservice.Entity.Soundbook;
import vt.vtservice.Entity.User;
import vt.vtservice.Repository.AudioRepository;
import vt.vtservice.Service.V2T;
import vt.vtservice.Utility.FFMpegUtil;
import vt.vtservice.Utility.TimeTool;


import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/V2T")
public class V2TController {
    @Autowired
    V2T v2T;
    @Autowired
    AudioRepository audioRepository;
    @Autowired
    UserDao userDao;
    @Autowired
    SoundbookDao soundbookDao;
    @PostMapping(value = "/gettext")
    public Map<String,Object> V2T(@RequestParam("file") MultipartFile file, @RequestParam("title")String title,@RequestParam("username")String username
                                  ){
        Map<String,Object> res = new HashMap<>();
        User user = userDao.findByUsername(username);
        Soundbook soundbook = new Soundbook();
        soundbook.setName(title);
        soundbook.setCreater(user);
        soundbook.setCreateTime(TimeTool.now());
        int bookId = soundbookDao.save(soundbook).getBookId();
        try {
            BufferedInputStream BIS = new BufferedInputStream(file.getInputStream());
            ByteArrayOutputStream BAOS = new ByteArrayOutputStream();
            byte[] reader = new byte[1024*1024];
            int input;
            while((input = BIS.read(reader))!=-1){
                BAOS.write(reader,0,input);
            }
            byte[] data = BAOS.toByteArray();
            System.out.println("v2t");
            res = v2T.V2T(data,title);
            File audio = new File(res.get("path").toString());
            byte[] audioBytes = FFMpegUtil.getBytes(audio);
            Aduio audioAduio = new Aduio();
            audioAduio.setAudio(audioBytes);
            audioAduio.setBaseAudio(data);
            audioAduio.setBookId(bookId);
            audioRepository.save(audioAduio);
            System.out.println(audioAduio.getBookId());
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            res.put("res","failed");
        }
        return res;
    }
}

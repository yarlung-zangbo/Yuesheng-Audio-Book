package yuesheng.tv.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import yuesheng.tv.DAO.SoundDao;
import yuesheng.tv.DAO.SoundbookDao;
import yuesheng.tv.DAO.UserDao;
import yuesheng.tv.Entity.Audio;
import yuesheng.tv.Entity.Sound;
import yuesheng.tv.Entity.Soundbook;
import yuesheng.tv.Entity.User;
import yuesheng.tv.Repository.AudioRepository;
import yuesheng.tv.Service.T2V;
import yuesheng.tv.Utility.FFMpegUtil;
import yuesheng.tv.Utility.TimeTool;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/T2V")
public class T2VController {
    @Autowired
    AudioRepository audioRepository;
    @Autowired
    SoundDao soundDao;
    @Autowired
    T2V t2v;
    @Autowired
    SoundbookDao soundbookDao;
    @Autowired
    UserDao userDao;
    @PostMapping(value = "/getaudio")
    public Map<String,Object> T2V(@RequestParam("file") MultipartFile file, @RequestParam("title")String title,@RequestParam("BookId")Integer bookid, @RequestParam(required = false,value= "per") Integer person,
                                  @RequestParam("username") String userName,@RequestParam("name") String name){
        /*User user = userDao.findByUsername(userName);
        Soundbook soundbook = new Soundbook();
        soundbook.setBookId(bookid);
        soundbook.setCreater(user);
        soundbook.setCreateTime(TimeTool.now());
        soundbook.setName(name);
        soundbookDao.save(soundbook);*/
        Map<String,Object> response = new HashMap();
        System.out.println(title);
        if(person==null) person = 3;
        if(person<0||person>4) {
            response.put("response","Requested voice nonexistent");
        }
        StringBuffer SB = new StringBuffer();
        String text="1";
        try {
            InputStream IPS = file.getInputStream();
            InputStreamReader ISR = new InputStreamReader(IPS,"UTF-8");
            BufferedReader BR = new BufferedReader(ISR);
            String s;
            while((s = BR.readLine())!=null) {
                SB.append(s);
            }
            text = SB.toString();
            IPS.close();
            ISR.close();
            BR.close();
        }
        catch(Exception e) {
            System.out.println("Exception: "+e.getMessage());
        }
        if(title.length()>64) {
            response.put("response","Title too long!");
            return response;
        }
        Map<String, Object> res= t2v.TextToAudioBinary(text,title,person);
        if(res.get("res").equals("success")) {
            File audio = new File(res.get("Path").toString());
            byte[] audioBytes = FFMpegUtil.getBytes(audio);
            Audio TA = new Audio();
            TA.setBookId(bookid);
            TA.setAudio(audioBytes);
            try {
                TA.setText(file.getBytes());
            }
            catch (Exception e) {
                System.out.println(e.getMessage());
                response.put("response","failure occurred");
                return response;
            }
            audioRepository.save(TA);
            response.put("reponse","Success");
            return response;
        }
        response.put("response",res.get("res"));
        return response;
    }
}

package yuesheng.tv.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import yuesheng.tv.DAO.SoundDao;
import yuesheng.tv.Entity.TextAudio;
import yuesheng.tv.Repository.TextAudioRepository;
import yuesheng.tv.Utility.T2V;

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
    TextAudioRepository textAudioRepository;
    @Autowired
    SoundDao soundDao;
    @PostMapping(value = "/getaudio")
    public Map<String,Object> T2V(@RequestParam("file") MultipartFile file, @RequestParam("title")String title,@RequestParam("BookId")Integer bookid){
        Map<String,Object> response = new HashMap();
        T2V t2v = new T2V();
        System.out.println(title);
        StringBuffer SB = new StringBuffer();
        String text="1";
        try {
            InputStream IPS = file.getInputStream();
            InputStreamReader ISR = new InputStreamReader(IPS,"UTF-8");
            BufferedReader BR = new BufferedReader(ISR);
            String s;
            while((s = BR.readLine())!=null) {
                System.out.println(s);
                SB.append(s);
            }
            text = SB.toString();
            IPS.close();
            ISR.close();
            BR.close();
            System.out.println(text);
        }
        catch(Exception e) {
            System.out.println("Exception: "+e.getMessage());
        }
        System.out.println(text);
        if(title.length()>64) {
            response.put("response","Title too long!");
            return response;
        }
        Map<String, Object> res= t2v.TextToAudioBinary(text,title);
        if(res.get("res").equals("success")) {
            File audio = new File(res.get("Path").toString());
            byte[] audioBytes = T2V.getBytes(audio);
            TextAudio TA = new TextAudio();
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
            textAudioRepository.save(TA);
            response.put("reponse","Success");
            return response;
        }
        response.put("response",res.get("res"));
        return response;
    }
}

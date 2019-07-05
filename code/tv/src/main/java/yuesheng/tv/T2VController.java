package yuesheng.tv;


import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/T2V")
public class T2VController {
    @PostMapping(value = "/getaudio")
    public Map<String,Object> T2V(@RequestParam("file") MultipartFile file, @RequestParam("title")String title){
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
        String res = t2v.TextToAudioBinary(text,title);

        response.put("response",res);
        return response;
    }
}

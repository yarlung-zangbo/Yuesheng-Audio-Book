package yuesheng.tv.Controller;

import com.baidu.aip.speech.AipSpeech;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import yuesheng.tv.Service.V2T;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/V2T")
public class V2TController {
    @Autowired
    V2T v2T;
    @PostMapping(value = "/gettext")
    public Map<String,Object> V2T(@RequestParam("file") MultipartFile file, @RequestParam("title")String title){
        Map<String,Object> res = new HashMap<>();
        try {
            BufferedInputStream BIS = new BufferedInputStream(file.getInputStream());
            ByteArrayOutputStream BAOS = new ByteArrayOutputStream();
            byte[] reader = new byte[1024*1024];
            int input;
            while((input = BIS.read(reader))!=-1){
                BAOS.write(reader,0,input);
            }
            byte[] data = BAOS.toByteArray();
            res = v2T.V2T(data,title);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            res.put("res","failed");
        }
        return res;
    }
}

package yuesheng.tv.ServiceImpl;

import com.baidu.aip.speech.AipSpeech;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import yuesheng.tv.Controller.V2TController;
import yuesheng.tv.Service.V2T;
import yuesheng.tv.Utility.TextConcatUtil;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.Map;

@Service
public class V2Timpl implements V2T {
    static String resoucesPath = "D:\\SEI\\week19\\YueSheng\\Yuesheng-Audio-Book\\code\\tv\\src\\main\\resources\\";
    public static final String APP_ID = "16682530";
    public static final String API_KEY = "AHo1rGgmZy29ULcCPyBVxcrY";
    public static final String SECRET_KEY = "rtwOSBH7kEjoVrghtfW52MNsWqLupi9Z";

    public static void main(String[] args) {
        V2T v2T = new V2Timpl();
    }
    @Override
    public Map<String,Object> V2T(byte[] data,String title) throws Exception{
        // 初始化一个AipSpeech
        AipSpeech client = new AipSpeech(APP_ID, API_KEY, SECRET_KEY);

        // 可选：设置网络连接参数
        client.setConnectionTimeoutInMillis(2000);
        client.setSocketTimeoutInMillis(60000);
        Map<String,Object> response = new HashMap<>();

        // 可选：设置log4j日志输出格式，若不设置，则使用默认配置
        // 也可以直接通过jvm启动参数设置此环境变量
        System.setProperty("aip.log4j.conf", resoucesPath);
        BufferedOutputStream BOS = new BufferedOutputStream(new FileOutputStream(new File(resoucesPath+title+".txt")));
        byte[] req = new byte[1024*1024];
        int length = data.length, ptr = 0, step = 0;
        System.out.println("length: "+length);
        String finalResult="";
        // 调用接口
        while(ptr<length){
            float mod = 15/16;
            if(length-ptr<1024*768) {
                step = length - ptr;
                mod = 1;
            }
            else step  = 1024*512;
            System.arraycopy(data,ptr,req,0,step);
            ptr+=step*mod;
            JSONObject res = client.asr(req, "wav", 16000, null);
            try {
                System.out.println(res.toString(2));
                System.out.println(res.get("result"));
                String result = res.get("result").toString();
                int strLength = result.length();
                finalResult = TextConcatUtil.ConcatText(finalResult,result.substring(2,strLength-2),30);
                System.out.println(finalResult);
            }
            catch (Exception e) {
                System.out.println(e.getMessage());
                response.put("res","failed");
                return response;
            }
        }
        BOS.write(finalResult.getBytes());
        BOS.flush();
        BOS.close();
        response.put("res","done.");
        return response;
    }
}

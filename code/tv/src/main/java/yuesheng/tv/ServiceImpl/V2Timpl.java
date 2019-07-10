package yuesheng.tv.ServiceImpl;

import com.baidu.aip.speech.AipSpeech;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import yuesheng.tv.Controller.V2TController;
import yuesheng.tv.Service.V2T;

@Service
public class V2Timpl implements V2T {
    static String resoucesPath = "D:\\SEI\\week19\\YueSheng\\Yuesheng-Audio-Book\\code\\tv\\src\\main\\resources\\log4j.properties";
    public static final String APP_ID = "16682530";
    public static final String API_KEY = "AHo1rGgmZy29ULcCPyBVxcrY";
    public static final String SECRET_KEY = "rtwOSBH7kEjoVrghtfW52MNsWqLupi9Z";

    public static void main(String[] args) {
        V2T v2T = new V2Timpl();
        System.out.println(v2T.V2T("D:\\SEI\\week19\\YueSheng\\Yuesheng-Audio-Book\\code\\tv\\src\\main\\resources\\动物0.wav"));
    }
    public String V2T(byte[] data) {
        // 初始化一个AipSpeech
        AipSpeech client = new AipSpeech(APP_ID, API_KEY, SECRET_KEY);

        // 可选：设置网络连接参数
        client.setConnectionTimeoutInMillis(2000);
        client.setSocketTimeoutInMillis(60000);


        // 可选：设置log4j日志输出格式，若不设置，则使用默认配置
        // 也可以直接通过jvm启动参数设置此环境变量
        System.setProperty("aip.log4j.conf", resoucesPath);
        byte[] req;
        int length = data.length, ptr = 0, step = 0;
        // 调用接口
        while(ptr<length){
            if(length-ptr<1024*1024) step = length - ptr;
            else step  = 1024*1024;
            req
            JSONObject res = client.asr(data, "wav", 16000, null);
            try {
                System.out.println(res.toString(2));
            }
            catch (Exception e) {
                System.out.println(e.getMessage());
                return "failed.";
            }
        }


        return "done.";
    }
}

package yuesheng.tv.Utility;
import com.baidu.aip.speech.AipSpeech;
import com.baidu.aip.speech.TtsResponse;
import com.baidu.aip.util.Util;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.audio.mp3.MP3AudioHeader;
import org.jaudiotagger.audio.mp3.MP3File;
import org.json.JSONObject;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class T2V {
    public static final String APP_ID = "16682530";
    public static final String API_KEY = "AHo1rGgmZy29ULcCPyBVxcrY";
    public static final String SECRET_KEY = "rtwOSBH7kEjoVrghtfW52MNsWqLupi9Z";

    public Map<String, Object> TextToAudioBinary(String text, String title) {
        // 初始化一个AipSpeech
        AipSpeech client = new AipSpeech(APP_ID, API_KEY, SECRET_KEY);

        // 可选：设置网络连接参数
        client.setConnectionTimeoutInMillis(2000);
        client.setSocketTimeoutInMillis(60000);

        // 可选：设置代理服务器地址, http和socket二选一，或者均不设置
        //client.setHttpProxy("proxy_host", 80);  // 设置http代理
        //client.setSocketProxy("proxy_host", proxy_port);  // 设置socket代理

        // 可选：设置log4j日志输出格式，若不设置，则使用默认配置
        // 也可以直接通过jvm启动参数设置此环境变量
        String resoucesPath = "D:\\SEI\\week19\\YueSheng\\Yuesheng-Audio-Book\\code\\tv\\src\\main\\resources\\";
                System.setProperty("aip.log4j.conf", resoucesPath+"log4j.properties");

        // 调用接口
        int length = text.length(), i = 0, j = 0, resLength;
        System.out.println(text);
        text.replaceAll("\n"," ");
        System.out.println(text);
        System.out.println(length);
        System.out.println(text.substring(0,3));
        byte[] result = new byte[0],oldResult;
        ByteArrayOutputStream ResBOS = new ByteArrayOutputStream(1024);
        while(i<length){
            j=i;
            char c = text.charAt(i);
            while(i<length&&i-j<1024&&c!='。'&&c!='！'&&c!='？'&&c!='，'&&c!='；'&&c!='…')
                c = text.charAt(i++);
            System.out.println("i= "+i);
            String excerpt = text.substring(j,i);
            String[] words = WordsParser.LineToWords(excerpt);
            HashMap<String,Object> options = new HashMap<String,Object>();
            options.put("vol",8);
            TtsResponse res = client.synthesis(excerpt, "zh", 1, options);
            System.out.println("Api returned");
            byte[] ResponseB = res.getData();
            ResBOS.write(ResponseB,0,ResponseB.length);
            JSONObject res1 = res.getResult();
            if(res1!=null) {
                System.out.println(res1.toString());
            }
        }
        result = ResBOS.toByteArray();
        if (result != null) {
            try {
                String voicepath = resoucesPath+title+".mp3";
                Util.writeBytesToFileSystem(result, voicepath);
                File read = new File(voicepath);
                File bg = new File(resoucesPath+"static\\Various Artists - 国际歌 (俄语).mp3");
                int readLength = T2V.getMp3TrackLength(read);
                System.out.println("Audio file length: "+ readLength);
                int bgLength = T2V.getMp3TrackLength(bg);
                System.out.println("readLength: "+readLength+", "+"bgLength: "+bgLength);
                byte[] bgBytes = T2V.getBytes(bg);
                ByteArrayOutputStream bos = new ByteArrayOutputStream(1000);
                String newBGPath = resoucesPath+"Internationale"+"_BG"+".mp3";
                File reWrite = new File(newBGPath);
                FileOutputStream RWFOS = new FileOutputStream(reWrite,false);
                BufferedOutputStream buos = new BufferedOutputStream(RWFOS);
                int times = (readLength / bgLength)+1;
                for(i = 0; i<times; i++){
                    System.out.println("i: " + i);
                    buos.write(bgBytes);
                }
                buos.flush();
                buos.close();
                RWFOS.close();
                String outPath = resoucesPath+title+"_With_BGM"+".mp3";
                System.out.println("Convertor entered");
                FFMpegUtil.convetor(voicepath, newBGPath,outPath);
                System.out.println("Convertor done.");
                HashMap<String, Object> res = new HashMap<>();
                res.put("res","success");
                res.put("Path",outPath);
                return res;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        HashMap<String,Object> res = new HashMap<>();
        res.put("res","failed");
        return res;
    }
    public static int getMp3TrackLength(File mp3File) {
        try {
            MP3File f = (MP3File) AudioFileIO.read(mp3File);
            MP3AudioHeader audioHeader = (MP3AudioHeader)f.getAudioHeader();
            return audioHeader.getTrackLength();
        } catch(Exception e) {
            return -1;
        }
    }
    public static byte[] getBytes(File file) {
        System.out.println(file.getName());
        byte[] buffer = null, result = new byte[0],oldresult = null;
        try {
            FileInputStream fis = new FileInputStream(file);
            DataInputStream dis = new DataInputStream(fis);
            ByteArrayOutputStream bos = new ByteArrayOutputStream(1000);
            byte[] b = new byte[1048576];
            int n,offset=0,available = fis.available();
            System.out.println(available);
            while ((n=dis.read(b))!=-1) {
                System.out.println(n+" bytes read.");
                bos.write(b, 0, n);
            }
            result = bos.toByteArray();
            fis.close();
            bos.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}

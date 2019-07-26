package yuesheng.tv.ServiceImpl;
import com.baidu.aip.speech.AipSpeech;
import com.baidu.aip.speech.TtsResponse;
import com.baidu.aip.util.Util;


import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import yuesheng.tv.DAO.SoundDao;
import yuesheng.tv.Entity.BGMContent;
import yuesheng.tv.Entity.Sound;
import yuesheng.tv.Repository.BGMContentRepository;
import yuesheng.tv.Service.T2V;
import yuesheng.tv.Utility.*;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class T2Vimpl implements T2V {
    public static final String APP_ID = "16682530";
    public static final String API_KEY = "AHo1rGgmZy29ULcCPyBVxcrY";
    public static final String SECRET_KEY = "rtwOSBH7kEjoVrghtfW52MNsWqLupi9Z";
    @Autowired
    private SoundDao soundDao;
    @Autowired
    CorrelationComputer correlationComputer;
    @Autowired
    BGMContentRepository bgmContentRepository;
    @Override
    public Map<String, Object> TextToAudioBinary(String text, String title, Integer person) {
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
        String resoucesPath = "src/main/resources/";
        System.setProperty("aip.log4j.conf", resoucesPath+"log4j.properties");

        // 调用接口

        BufferedOutputStream ResBOS;
        String voicepath = "",integratedBGM,bgmOutPath = resoucesPath+"static/bgm/"+"BGM0.mp3",OBGMOutPath;
        int bgmNo=0;
        try {
            int length = text.length(), i = 0, j = 0, resLength;
            text.replaceAll("\n"," ");
            System.out.println(length);
            int appendCount=0,count = 0,plannedLength = 0;
            while(i<length){
                String Ovoicepath = voicepath;
                if(appendCount==0) voicepath = resoucesPath+title+appendCount+".mp3";
                else {
                    voicepath = resoucesPath+"res.mp3";
                }
                File AudioFile = new File(voicepath);
                FileOutputStream AudioOutput = new FileOutputStream(AudioFile, false);
                ResBOS = new BufferedOutputStream(AudioOutput);
                j=i;
                char c = text.charAt(i);
                while(i<length&&i-j<1024&&c!='。'&&c!='！'&&c!='？'&&c!='；'&&c!='…')
                    c = text.charAt(i++);
                System.out.println("i= "+i);
                count+=i-j;
                String excerpt = text.substring(j,i);
                List<String> words = SoundEffect.VerifySE(excerpt);
                HashMap<String,Object> options = new HashMap<String,Object>();
                options.put("vol",13);
                options.put("per",person);
                options.put("spd",4);
                TtsResponse res = client.synthesis(excerpt, "zh", 1, options);
                System.out.println("Api returned");
                byte[] ResponseB = res.getData();
                ResBOS.write(ResponseB,0,ResponseB.length);
                ResBOS.flush();
                ResBOS.close();
                AudioOutput.close();
                if(appendCount!=0){
                    String tick2;
                    tick2 = resoucesPath+"res.mpg";
                    FFMpegUtil.tickFormat(voicepath,tick2);
                    appendCount++;
                    voicepath = resoucesPath+title+appendCount+".mpg";
                    FFMpegUtil.concatenator(Ovoicepath,tick2,voicepath);
                    File f2 = new File(Ovoicepath);
                    f2.delete();
                }
                else{
                    Ovoicepath = voicepath;
                    voicepath = resoucesPath+title+appendCount+".mpg";
                    FFMpegUtil.tickFormat(Ovoicepath,voicepath);
                    File f = new File(Ovoicepath);
                    f.delete();
                    appendCount++;
                }
                for(int l = 0; l<words.size(); l++) {
                    System.out.println(words.get(l));
                    Sound sound = soundDao.findByName(words.get(l));
                    if(sound!=null) {
                        byte[] soundEffect = sound.getContent();
                        System.out.println(soundEffect.length);
                        System.out.println(sound.getName()+" found and added.");
                        String found = resoucesPath+"static\\soundeffects\\"+sound.getName()+".mp3";
                        try{
                            Util.writeBytesToFileSystem(sound.getContent(),found);
                        }
                        catch (Exception e) { }
                        String tick2;
                        tick2 = resoucesPath+"static\\soundeffects\\"+appendCount+"_"+l+".mpg";
                        FFMpegUtil.tickFormat(found,tick2);
                        appendCount++;
                        Ovoicepath = voicepath;
                        voicepath = resoucesPath+title+appendCount+".mpg";
                        System.out.println("VoicePath: "+voicepath);
                        FFMpegUtil.concatenator(Ovoicepath,tick2,voicepath);
                        File f2 = new File(tick2);
                        File f3 = new File(Ovoicepath);
                        f2.delete();
                        f3.delete();
                    }
                }
                if(count>1024||i>=length) {
                    OBGMOutPath = bgmOutPath;
                    bgmOutPath = resoucesPath+"static/bgm/"+"BGM"+bgmNo+".mp3";
                    String measure = resoucesPath+"static/bgm/measure.mp3";
                    FFMpegUtil.tickFormat(voicepath,measure);
                    int currentLength = FFMpegUtil.getMp3TrackLength(new File(measure));
                    (new File(measure)).delete();
                    System.out.println(currentLength);
                    int bgmLength = currentLength - plannedLength;
                    Map<String,Integer> Analysis = EmotionAnalysis.parseText(text.substring(i-count,i));
                    String sectionBGMName = correlationComputer.BGMPicker(Analysis);
                    BGMContent sectionBGMContent = bgmContentRepository.findByName(sectionBGMName);
                    String sectionBGMPath = resoucesPath+"static/bgm/"+sectionBGMName+".mp3";
                    Util.writeBytesToFileSystem(sectionBGMContent.getContent(),sectionBGMPath);
                    sectionBGMPath = FFMpegUtil.LowerVolumn(sectionBGMPath);
                    int sectionBGMLength = FFMpegUtil.getMp3TrackLength(new File(sectionBGMPath));
                    int times = (bgmLength / sectionBGMLength) + 1;
                    String newBGMPath = resoucesPath+"static/bgm/"+sectionBGMName+"_BGM.mp3";
                    BufferedOutputStream buos = new BufferedOutputStream(new FileOutputStream(new File(newBGMPath)));
                    for(int m=0;m<times;m++){
                        buos.write(sectionBGMContent.getContent());
                    }
                    buos.flush();
                    buos.close();
                    String trimmedBGMPath = resoucesPath+"static/bgm/"+sectionBGMName+"_TBGM.mp3";
                    FFMpegUtil.cutAudio(newBGMPath,trimmedBGMPath,bgmLength);
                    if(plannedLength==0){
                        FFMpegUtil.cutAudio(trimmedBGMPath,bgmOutPath,bgmLength);
                    }
                    else{
                        FFMpegUtil.concatenator(OBGMOutPath,trimmedBGMPath,bgmOutPath);
                    }
                    bgmNo++;
                    plannedLength = currentLength;
                    count=0;
                }
                JSONObject res1 = res.getResult();
                if(res1!=null) {
                    System.out.println(res1.toString());
                }
            }
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            HashMap<String, Object> res = new HashMap<>();
            res.put("res","failed");
            return res;
        }
            try {
                String tick = resoucesPath+title+".mp3";
                FFMpegUtil.tickFormat(voicepath,tick);
                String outPath = resoucesPath+title+"_With_BGM"+".mp3";
                System.out.println("Convertor entered");
                FFMpegUtil.convetor(tick, bgmOutPath,outPath);
                System.out.println("Convertor done.");
                HashMap<String, Object> res = new HashMap<>();
                res.put("res","success");
                res.put("Path",outPath);
                return res;
            } catch (Exception e) {
                e.printStackTrace();
            }
        HashMap<String,Object> res = new HashMap<>();
        res.put("res","failed");
        return res;
    }
}
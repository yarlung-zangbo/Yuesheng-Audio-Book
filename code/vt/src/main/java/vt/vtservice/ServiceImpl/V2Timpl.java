package vt.vtservice.ServiceImpl;

import com.baidu.aip.speech.AipSpeech;
import com.baidu.aip.util.Util;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vt.vtservice.DAO.SoundDao;
import vt.vtservice.Entity.BGMContent;
import vt.vtservice.Entity.EmotionMap;
import vt.vtservice.Entity.Sound;
import vt.vtservice.Repository.BGMContentRepository;
import vt.vtservice.Service.V2T;
import vt.vtservice.Utility.*;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class V2Timpl implements V2T {
    static String resoucesPath = "src\\main\\resources\\";
    public static final String APP_ID = "16682530";
    public static final String API_KEY = "AHo1rGgmZy29ULcCPyBVxcrY";
    public static final String SECRET_KEY = "rtwOSBH7kEjoVrghtfW52MNsWqLupi9Z";

    @Autowired
    SoundDao soundDao;
    @Autowired
    CorrelationComputer correlationComputer;
    @Autowired
    BGMContentRepository bgmContentRepository;
    @Override
    public Map<String,Object> V2T(byte[] data,String title) throws Exception{
        // 初始化一个AipSpeech
        String product =  resoucesPath+"static\\"+title+".mp3";
        String output = resoucesPath+"static\\"+title+0+".mpg",OOutput = output;
        String bgmOutPath = resoucesPath+"static\\bgm\\"+"BGM0.mp3",OBGMOutPath;
        StringBuffer textBuffer=new StringBuffer();
        int appendage=0,bgmNo=0,plannedLength=0;
        AipSpeech client = new AipSpeech(APP_ID, API_KEY, SECRET_KEY);

        // 可选：设置网络连接参数
        client.setConnectionTimeoutInMillis(2000);
        client.setSocketTimeoutInMillis(60000);
        Map<String,Object> response = new HashMap<>();

        // 可选：设置log4j日志输出格式，若不设置，则使用默认配置
        // 也可以直接通过jvm启动参数设置此环境变量
        System.setProperty("aip.log4j.conf", resoucesPath);
        BufferedOutputStream BOS = new BufferedOutputStream(new FileOutputStream(new File(resoucesPath+title+".txt")));
        int length = data.length, ptr = 0, step = 0;
        System.out.println("length: "+length);
        String originalAudio = resoucesPath+"static\\"+title+".wav";
        Util.writeBytesToFileSystem(data,originalAudio);
        Integer segments = AudioSplitUtil.SplitAudio(originalAudio);
        int count=0;
        for(int i=0;i<segments;i++){
            String segmentPath = resoucesPath+"static\\chunks\\"+String.format("%04d",i)+".wav";
            String tSegmentPath  = resoucesPath+"static\\chunks\\"+String.format("%04d",i)+".mpg";
            FFMpegUtil.tickFormat(segmentPath,tSegmentPath);
            if(appendage==0) FFMpegUtil.tickFormat(tSegmentPath,output);
            else {
                OOutput = output;
                output = resoucesPath+"static\\"+title+appendage+".wav";
                FFMpegUtil.concatenator(OOutput,tSegmentPath,output);
                (new File(OOutput)).delete();
            }
            appendage++;
            HashMap<String, Object> options = new HashMap<String, Object>();
            options.put("dev_pid",1537);
            JSONObject res = client.asr(segmentPath,"wav",16000,options);
            System.out.println(res.get("err_msg"));
            String result = res.get("result").toString();
            String line = result.substring(2,result.length()-2);
            count+=line.length();
            textBuffer.append(line);
            List<String> soundEffects = SoundEffect.VerifySE(line);
            int len = soundEffects.size();
            for(int j=0;j<len;j++){
                Sound sound = soundDao.findByName(soundEffects.get(j));
                if(sound!=null){
                    System.out.println(sound.getName()+" found and added.");
                    byte[] soundEffect = sound.getContent();
                    String soundEffectPath = resoucesPath+"static\\soundeffects\\"+sound.getName()+".mp3";
                    String tSoundEffectPath = soundEffectPath.substring(0,soundEffectPath.length()-4)+".mpg";
                    Util.writeBytesToFileSystem(soundEffect,soundEffectPath);
                    FFMpegUtil.tickFormat(soundEffectPath,tSoundEffectPath);
                    OOutput = output;
                    output = resoucesPath+"static\\"+title+appendage+".mpg";
                    FFMpegUtil.concatenator(OOutput,tSoundEffectPath,output);
                    (new File(OOutput)).delete();
                    (new File(soundEffectPath)).delete();
                    appendage++;
                }
            }
            if(count>1024||i>=segments-1){
                OBGMOutPath = bgmOutPath;
                bgmOutPath = resoucesPath+"static\\bgm\\"+"BGM"+bgmNo+".mp3";
                System.out.println("here");
                String measure = resoucesPath+"static\\bgm\\measure.mp3";
                System.out.println("output: "+output);
                FFMpegUtil.tickFormat(output,measure);
                int currentLength = FFMpegUtil.getMp3TrackLength(new File(measure));
                (new File(measure)).delete();
                System.out.println(currentLength);
                int bgmLength = currentLength - plannedLength;
                Map<String,Integer> Analysis = EmotionAnalysis.parseText(line);
                String sectionBGMName = correlationComputer.BGMPicker(Analysis);
                BGMContent sectionBGMContent = bgmContentRepository.findByName(sectionBGMName);
                String sectionBGMPath = resoucesPath+"static\\bgm\\"+sectionBGMName+".mp3";
                Util.writeBytesToFileSystem(sectionBGMContent.getContent(),sectionBGMPath);
                sectionBGMPath = FFMpegUtil.LowerVolumn(sectionBGMPath);
                int sectionBGMLength = FFMpegUtil.getMp3TrackLength(new File(sectionBGMPath));
                int times = (bgmLength / sectionBGMLength) + 1;
                String newBGMPath = resoucesPath+"static\\bgm\\"+sectionBGMName+"_BGM.mp3";
                BufferedOutputStream buos = new BufferedOutputStream(new FileOutputStream(new File(newBGMPath)));
                for(int m=0;m<times;m++){
                    buos.write(sectionBGMContent.getContent());
                }
                buos.flush();
                buos.close();
                String trimmedBGMPath = resoucesPath+"static\\bgm\\"+sectionBGMName+"_TBGM.mp3";
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
        }
        FFMpegUtil.tickFormat(output,product);
        /*
        String bgmName = correlationComputer.BGMPicker(report);
        BGMContent bgmContent = bgmContentRepository.findByName(bgmName);
        System.out.println(bgmContent.getName());
        String bgmPath = resoucesPath+"static\\bgm\\"+bgmName+".mp3";
        Util.writeBytesToFileSystem(bgmContent.getContent(),bgmPath);
        bgmPath = FFMpegUtil.LowerVolumn(bgmPath);
        File read = new File(product);
        File bg = new File(bgmPath);
        int readLength = FFMpegUtil.getMp3TrackLength(read);
        System.out.println("Audio file length: "+ readLength);
        int bgLength = FFMpegUtil.getMp3TrackLength(bg);
        System.out.println("readLength: "+readLength+", "+"bgLength: "+bgLength);
        byte[] bgBytes = FFMpegUtil.getBytes(bg);
        ByteArrayOutputStream bos = new ByteArrayOutputStream(1000);
        String newBGPath = resoucesPath+"\\static\\bgm"+bgmName+"_BG"+".mp3";
        File reWrite = new File(newBGPath);
        FileOutputStream RWFOS = new FileOutputStream(reWrite,false);
        BufferedOutputStream buos = new BufferedOutputStream(RWFOS);
        int times = (readLength / bgLength)+1;
        for(int i = 0; i<times; i++){
            System.out.println("i: " + i);
            buos.write(bgBytes);
        }
        buos.flush();
        buos.close();
        RWFOS.close();
        */
        String outPath = resoucesPath+"static\\"+title+"_With_BGM"+".mp3";
        System.out.println("Convertor entered");
        FFMpegUtil.convetor(product, bgmOutPath,outPath);
        System.out.println("Convertor done.");
        System.out.println(textBuffer.toString());
        BOS.write(textBuffer.toString().getBytes());
        BOS.flush();
        BOS.close();
        response.put("res","done.");
        System.out.println();
        response.put("path",outPath);
        return response;
    }
}

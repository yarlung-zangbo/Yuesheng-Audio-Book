package vt.vtservice.Utility;

import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.audio.mp3.MP3AudioHeader;
import org.jaudiotagger.audio.mp3.MP3File;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FFMpegUtil {
    private static String ffmpegEXE = "ffmpeg";

    /*public static void main(String[] args) throws Exception {
        //concatenator("D:\\SEI\\week19\\YueSheng\\Yuesheng-Audio-Book\\code\\tv\\src\\main\\resources\\0.mpg","D:\\SEI\\week19\\YueSheng\\Yuesheng-Audio-Book\\code\\tv\\src\\main\\resources\\static\\3.mpg","D:\\SEI\\week19\\YueSheng\\Yuesheng-Audio-Book\\code\\tv\\src\\main\\resources\\风1.mp3");
        System.out.println("success!");
    }
    */
    /**
     * @param audioInputPath1 音频1的路径
     * @param audioInputPath2 音频2的路径
     * @param OutPath         合成之后音频的路径
     * @throws Exception
     */
    // 将两个音频结合，同时生成结合后的文件
    // ffmpeg -i input1.wav -i input2.wav -filter_complex amix=inputs=2:duration=shortest output.wav
    public static void convetor(String audioInputPath1, String audioInputPath2, String OutPath)
            throws Exception {

        List<String> command = new ArrayList<String>();
        command.add(ffmpegEXE);
        command.add("-y");
        command.add("-i");
        command.add(audioInputPath1);
        command.add("-i");
        command.add(audioInputPath2);
        command.add("-filter_complex");
        command.add("amix=inputs=2:duration=shortest");
        command.add(OutPath);
        ProcessBuilder builder = new ProcessBuilder(command);
        Process process = null;
        try {
            process = builder.start();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        // 对流进行处理
        InputStream errorStream = process.getErrorStream();
        InputStreamReader inputStreamReader = new InputStreamReader(errorStream);
        BufferedReader br = new BufferedReader(inputStreamReader);

        String line = "";
        while ((line = br.readLine()) != null) {
        }
        if (br != null) {
            br.close();
        }
        if (inputStreamReader != null) {
            inputStreamReader.close();
        }
        if (errorStream != null) {
            errorStream.close();
        }

    }
    public static void concatenator(String audioPath1,String audioPath2, String OutPath) throws Exception {
        BufferedInputStream boi1 = new BufferedInputStream(new FileInputStream(new File(audioPath1)));
        BufferedInputStream boi2 = new BufferedInputStream(new FileInputStream(new File(audioPath2)));
        SequenceInputStream SIS = new SequenceInputStream(boi1,boi2);
        BufferedOutputStream BOM= new BufferedOutputStream(new FileOutputStream(new File(OutPath)));
        int i=0;
        while((i=SIS.read())!=-1){
            BOM.write(i);
        }
        BOM.flush();
        SIS.close();
        BOM.close();
        boi1.close();
        boi2.close();
    }

    public static void tickFormat(String file,String output){
        List<String> command = new ArrayList<String>();
        command.add(ffmpegEXE);
        command.add("-y");
        command.add("-i");
        command.add(file);
        command.add("-qscale");
        command.add("0");
        command.add(output);
        ProcessBuilder builder = new ProcessBuilder(command);
        Process process = null;
        try {
            process = builder.start();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        // 对流进行处理
        InputStream errorStream = process.getErrorStream();
        InputStreamReader inputStreamReader = new InputStreamReader(errorStream);
        BufferedReader br = new BufferedReader(inputStreamReader);
        try {
            String line = "";
            while ((line = br.readLine()) != null) {
            }
            if (br != null) {
                br.close();
            }
            if (inputStreamReader != null) {
                inputStreamReader.close();
            }
            if (errorStream != null) {
                errorStream.close();
            }
        }
        catch (Exception E){}
    }
    public static String LowerVolumn(String path){
        String output = path.substring(0,path.length()-4)+"_L.mp3";
        List<String> command = new ArrayList<String>();
        command.add(ffmpegEXE);
        command.add("-y");
        command.add("-i");
        command.add(path);
        command.add("-af");
        command.add("volume = -10dB");
        command.add(output);
        ProcessBuilder builder = new ProcessBuilder(command);
        Process process = null;
        try {
            process = builder.start();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        // 对流进行处理
        InputStream errorStream = process.getErrorStream();
        InputStreamReader inputStreamReader = new InputStreamReader(errorStream);
        BufferedReader br = new BufferedReader(inputStreamReader);
        try {
            String line = "";
            while ((line = br.readLine()) != null) {
            }
            if (br != null) {
                br.close();
            }
            if (inputStreamReader != null) {
                inputStreamReader.close();
            }
            if (errorStream != null) {
                errorStream.close();
            }
        }
        catch (Exception E){}
        return output;
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
    public static int getMp3TrackLength(File mp3File) {
        try {
            MP3File f = (MP3File) AudioFileIO.read(mp3File);
            MP3AudioHeader audioHeader = (MP3AudioHeader)f.getAudioHeader();
            return audioHeader.getTrackLength();
        } catch(Exception e) {
            return -1;
        }
    }
}

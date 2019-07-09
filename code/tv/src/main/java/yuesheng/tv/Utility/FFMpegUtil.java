package yuesheng.tv.Utility;

import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.audio.mp3.MP3AudioHeader;
import org.jaudiotagger.audio.mp3.MP3File;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FFMpegUtil {
    private static String ffmpegEXE = "ffmpeg";

    public static void main(String[] args) throws Exception {
        System.out.println("success!");
    }

    /**
     * @param audioInputPath1 音频1的路径
     * @param audioInputPath2 音频2的路径
     * @param OutPath         合成之后音频的路径
     * @throws Exception
     */
    // 将两个音频结合，同时生成结合之后的文件
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
        List<String> command = new ArrayList<String>();
        command.add("cat");
        command.add(audioPath1);
        command.add(audioPath2);
        command.add("|");
        command.add(ffmpegEXE);
        command.add("-f");
        command.add("mpeg");
        command.add("-i");
        command.add("-");
        command.add("-qscale");
        command.add("0");
        command.add("-vcodec");
        command.add("mpeg3");
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

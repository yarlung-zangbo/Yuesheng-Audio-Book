package yuesheng.tv.Utility;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class FFMpegUtil {
    private static String ffmpegEXE = "D:\\SummerProject\\ffmpeg-20190702-231d0c8-win64-static\\bin\\ffmpeg.exe";

    public static void main(String[] args) throws Exception {
        convetor("D:\\SEI\\week19\\tv\\src\\main\\resources\\P.mp3","D:\\SEI\\week19\\tv\\PGBG.mp3",
                "D:\\SEI\\week19\\tv\\src\\main\\resources\\PTest.mp3");
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
}

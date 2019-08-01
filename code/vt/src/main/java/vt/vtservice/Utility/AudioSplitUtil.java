package vt.vtservice.Utility;

import org.springframework.beans.factory.annotation.Value;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;

public class AudioSplitUtil {
    public static Integer SplitAudio(String path,String resourcesPath){
        String result = "";
        System.out.println(resourcesPath);
        try {
            Process process = Runtime.getRuntime().exec("python "+"AudioSpliter.py " +path);
//            process.waitFor();
            InputStreamReader ir = new InputStreamReader(process.getInputStream());
            LineNumberReader input = new LineNumberReader(ir);
            result = input.readLine();
            input.close();
            ir.close();
//            process.waitFor();
        } catch (IOException e) {
            System.out.println("调用python脚本并读取结果时出错：" + e.getMessage());
        }
        System.out.println(result);
        return Integer.valueOf(result);
    }
}

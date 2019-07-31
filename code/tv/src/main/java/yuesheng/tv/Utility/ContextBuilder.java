package yuesheng.tv.Utility;

import java.io.File;

public class ContextBuilder {
    static int count=0;
    public static void buildContext(String resourcesPath){
        if(count==0) {
            String root = resourcesPath.substring(0,resourcesPath.length()-1);
            File tvF = new File(root);
            if(!tvF.exists()) tvF.mkdir();
            String staticPath = resourcesPath + "static";
            File staticFile = new File(staticPath);
            if (!staticFile.exists()) staticFile.mkdir();
            String processPath = staticPath + "/process";
            File processFile = new File(processPath);
            if (!processFile.exists()) processFile.mkdir();
            String bgmPath = staticPath + "/bgm";
            File bgmFile = new File(bgmPath);
            if (!bgmFile.exists()) bgmFile.mkdir();
            String soundPath = staticPath + "/soundeffects";
            File soundFile = new File(soundPath);
            if (!soundFile.exists()) soundFile.mkdir();
            count++;
        }
    }
}

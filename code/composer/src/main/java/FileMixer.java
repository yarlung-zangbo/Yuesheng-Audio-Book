import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileMixer {
    public static void main(String[] args) throws IOException {
        FileMixer();
        System.out.println("success");
    }

    public static void FileMixer() throws IOException {
        Path path1 = Paths.get("/Users/MMore/Desktop/AT1.wav");
        Path path2 = Paths.get("/Users/MMore/Desktop/AT2.wav");
        byte[] byte1 = Files.readAllBytes(path1);
        byte[] byte2 = Files.readAllBytes(path2);
        byte[] out = new byte[byte1.length];
        for (int i=0; i<byte1.length; i++)
            out[i] = (byte) ((byte1[i] + byte2[i]) >> 1);

        getFileByBytes(out,"/Users/MMore/Desktop", "AT3.wav");
    }

    public static void getFileByBytes(byte[] bytes, String filePath, String fileName) {
        BufferedOutputStream bos = null;
        FileOutputStream fos = null;
        File file = null;
        try {
            File dir = new File(filePath);
            if (!dir.exists() && dir.isDirectory()) {// 判断文件目录是否存在
                dir.mkdirs();
            }
            file = new File(filePath + "/" + fileName);
            fos = new FileOutputStream(file);
            bos = new BufferedOutputStream(fos);
            bos.write(bytes);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (bos != null) {
                try {
                    bos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

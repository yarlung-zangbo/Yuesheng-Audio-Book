package yuesheng.tv.Utility;

import com.hankcs.hanlp.collection.AhoCorasick.AhoCorasickDoubleArrayTrie;
import com.hankcs.hanlp.dictionary.CoreDictionary;
import com.hankcs.hanlp.dictionary.CustomDictionary;
import org.springframework.core.io.ClassPathResource;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EmotionAnalysis
{
    static int emvalue_PA, emvalue_PE, emvalue_PD, emvalue_PH, emvalue_PG, emvalue_PB, emvalue_PK,
                emvalue_NA, emvalue_NB, emvalue_NJ, emvalue_NH, emvalue_PF, emvalue_NI, emvalue_PC,
                emvalue_NC, emvalue_NG, emvalue_NE, emvalue_ND, emvalue_NN, emvalue_NK, emvalue_NL;
    static int called=0;
    static List<String> emotionList = new ArrayList<>();
    public static void initializer() throws IOException{
        System.out.println("EmotionAnalysis initializing");
        ClassPathResource file = new ClassPathResource("static/emotion.dic");
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(file.getInputStream()));
        String str = null;
        while((str = bufferedReader.readLine()) != null)
        {
            String [] arr = str.split("\\s+");
            String emotion = "";
            for (int i = 1; i < arr.length; i++) {
                emotion = emotion + arr[i] + " ";
            }
            CustomDictionary.add(arr[0], emotion);
            emotionList.add(arr[0]);
        }
        System.out.println("EmotionAnalysis initialized");
        bufferedReader.close();

    }
    public static Map<String,Integer> parseText(String text) throws IOException {
        if(called==0) initializer();
        called+=1;
        // AhoCorasickDoubleArrayTrie自动机扫描文本中出现的自定义词语
        final char[] charArray = text.toCharArray();
        CustomDictionary.parseText(charArray, new AhoCorasickDoubleArrayTrie.IHit<CoreDictionary.Attribute>()
        {
            @Override
            public void hit(int begin, int end, CoreDictionary.Attribute value)
            {
                String wd = new String(charArray, begin, end - begin);
                if (emotionList.contains(wd)){
                    String em = value.toString().substring(0,2);
                    if (em.equals("PA")){
                        emvalue_PA += Integer.valueOf(value.toString().substring(3,4));
                    }
                    else if (em.equals("PE")) {
                        emvalue_PE += Integer.valueOf(value.toString().substring(3,4));
                    }
                    else if (em.equals("PD")) {
                        emvalue_PD += Integer.valueOf(value.toString().substring(3,4));
                    }
                    else if (em.equals("PH")) {
                        emvalue_PH += Integer.valueOf(value.toString().substring(3,4));
                    }
                    else if (em.equals("PC")) {
                        emvalue_PC += Integer.valueOf(value.toString().substring(3,4));
                    }
                    else if (em.equals("PG")) {
                        emvalue_PG += Integer.valueOf(value.toString().substring(3,4));
                    }
                    else if (em.equals("PB")) {
                        emvalue_PB += Integer.valueOf(value.toString().substring(3,4));
                    }
                    else if (em.equals("PK")) {
                        emvalue_PK += Integer.valueOf(value.toString().substring(3,4));
                    }
                    else if (em.equals("NA")) {
                        emvalue_NA += Integer.valueOf(value.toString().substring(3,4));
                    }
                    else if (em.equals("NB")) {
                        emvalue_NB += Integer.valueOf(value.toString().substring(3,4));
                    }
                    else if (em.equals("NJ")) {
                        emvalue_NJ += Integer.valueOf(value.toString().substring(3,4));
                    }
                    else if (em.equals("NH")) {
                        emvalue_NH += Integer.valueOf(value.toString().substring(3,4));
                    }
                    else if (em.equals("PF")) {
                        emvalue_PF += Integer.valueOf(value.toString().substring(3,4));
                    }
                    else if (em.equals("NI")) {
                        emvalue_NI += Integer.valueOf(value.toString().substring(3,4));
                    }
                    else if (em.equals("NC")) {
                        emvalue_NC += Integer.valueOf(value.toString().substring(3,4));
                    }
                    else if (em.equals("NG")) {
                        emvalue_NG += Integer.valueOf(value.toString().substring(3,4));
                    }
                    else if (em.equals("NE")) {
                        emvalue_NE += Integer.valueOf(value.toString().substring(3,4));
                    }
                    else if (em.equals("ND")) {
                        emvalue_ND += Integer.valueOf(value.toString().substring(3,4));
                    }
                    else if (em.equals("NN")) {
                        emvalue_NN += Integer.valueOf(value.toString().substring(3,4));
                    }
                    else if (em.equals("NK")) {
                        emvalue_NK += Integer.valueOf(value.toString().substring(3,4));
                    }
                    else if (em.equals("NL")) {
                        emvalue_NL += Integer.valueOf(value.toString().substring(3,4));
                    }
                    System.out.printf("[%d:%d] : %s %s\n", begin, end, wd, value);
                }
            }
        });

        // 自定义词典在所有分词器中都有效
        // System.out.println(HanLP.segment(text));
        System.out.println("\n" + "情感分析报告：");
        System.out.println("快乐(PA): " + emvalue_PA);
        System.out.println("安心(PE): " + emvalue_PE);
        System.out.println("惊奇(PC): " + emvalue_PC);
        System.out.println("尊敬(PD): " + emvalue_PD);
        System.out.println("赞扬(PH): " + emvalue_PH);
        System.out.println("相信(PG): " + emvalue_PG);
        System.out.println("喜爱(PB): " + emvalue_PB);
        System.out.println("祝愿(PK): " + emvalue_PK);
        System.out.println("愤怒(NA): " + emvalue_NA);
        System.out.println("悲伤(NB): " + emvalue_NB);
        System.out.println("失望(NJ): " + emvalue_NJ);
        System.out.println("内疚(NH): " + emvalue_NH);
        System.out.println("思念(PF): " + emvalue_PF);
        System.out.println("慌乱(NI): " + emvalue_NI);
        System.out.println("恐惧(NC): " + emvalue_NC);
        System.out.println("害羞(NG): " + emvalue_NG);
        System.out.println("烦闷(NE): " + emvalue_NE);
        System.out.println("憎恶(ND): " + emvalue_ND);
        System.out.println("贬责(NN): " + emvalue_NN);
        System.out.println("妒忌(NK): " + emvalue_NK);
        System.out.println("怀疑(NL): " + emvalue_NL);

        HashMap<String,Integer> report = new HashMap<>();
        report.put("PA",emvalue_PA);
        report.put("PE",emvalue_PE);
        report.put("PC",emvalue_PC);
        report.put("PD",emvalue_PD);
        report.put("PH",emvalue_PH);
        report.put("PG",emvalue_PG);
        report.put("PB",emvalue_PB);
        report.put("PK",emvalue_PK);
        report.put("NA",emvalue_NA);
        report.put("NB",emvalue_NB);
        report.put("NJ",emvalue_NJ);
        report.put("NH",emvalue_NH);
        report.put("PF",emvalue_PF);
        report.put("NI",emvalue_NI);
        report.put("NC",emvalue_NC);
        report.put("NG",emvalue_NG);
        report.put("NE",emvalue_NE);
        report.put("ND",emvalue_ND);
        report.put("NN",emvalue_NN);
        report.put("NK",emvalue_NK);
        report.put("NL",emvalue_NL);
        emvalue_PA=0;
        emvalue_PE=0;
        emvalue_PD=0;
        emvalue_PC=0;
        emvalue_PH=0;
        emvalue_NA=0;
        emvalue_PG=0;
        emvalue_PB=0;
        emvalue_PK=0;
        emvalue_NB=0;
        emvalue_NJ=0;
        emvalue_NH=0;
        emvalue_PF=0;
        emvalue_NI=0;
        emvalue_NC=0;
        emvalue_NG=0;
        emvalue_NE=0;
        emvalue_ND=0;
        emvalue_NN=0;
        emvalue_NK=0;
        emvalue_NL=0;
        return report;
    }
}

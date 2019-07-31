package vt.vtservice.Utility;

import com.hankcs.hanlp.HanLP;
import com.hankcs.hanlp.collection.AhoCorasick.AhoCorasickDoubleArrayTrie;
import com.hankcs.hanlp.dictionary.CoreDictionary;
import com.hankcs.hanlp.dictionary.CustomDictionary;
import com.hankcs.hanlp.mining.word2vec.WordVectorModel;
import com.hankcs.hanlp.seg.common.Term;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * 演示用户词典的动态增删
 *
 * @author hankcs
 */
public class SoundEffect
{
    @Value("${resourcesPath}")
    String resourcesPath;
    static int called = 0;
    static WordVectorModel wordVectorModel;
    static List<String> audioList = new ArrayList<>();  // 音效名的List
    public static void initializer(String resourcesPath){
        System.out.println("SoundEffect initializing.");
            try {
                wordVectorModel = new WordVectorModel(resourcesPath+"/static/res.txt");
                System.out.println("SoundEffect 1");
                String str = null;
                ClassPathResource file = new ClassPathResource("static/audio.dic");
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(file.getInputStream()));
                while((str = bufferedReader.readLine()) != null)
                {
                    CustomDictionary.add(str);  // 将音效名加入自定义词典
                    audioList.add(str);     // 将音效名加入音效名的List
                }
                System.out.println("SoundEffect initialized.");
                bufferedReader.close();
            } catch (IOException ex) {
                System.out.println(ex.toString());
            }
    }
    public static List<String> VerifySE(String text,String resourcesPath) throws IOException {

        // AhoCorasickDoubleArrayTrie自动机扫描文本中出现的自定义词语

        final char[] charArray = text.toCharArray();
        CustomDictionary.parseText(charArray, new AhoCorasickDoubleArrayTrie.IHit<CoreDictionary.Attribute>()
        {
            @Override
            public void hit(int begin, int end, CoreDictionary.Attribute value)
            {
                // String parseword = new String(charArray, begin, end - begin);
            }
        });

        // 分词
        List<Term> parselist = HanLP.segment(text);
        if(called==0) initializer(resourcesPath);
        called+=1;
        List<String> soundEffects = new ArrayList<>();
        for(int i = 0 ; i < parselist.size() ; i++) {
            String word = parselist.get(i).word;    // 分词所得词语
            if (audioList.contains(word)){  // 如果词语有匹配的音效，则符合
                System.out.println(word+" "+"hit");
                soundEffects.add(word);
            }
            else{   // 否则寻找最大匹配子串
                int substrsize = 0;
                String substr = "";
                for(int j = 0; j < word.length(); j++){
                    for (int k = j+1; k<=word.length(); k++){
                        if (audioList.contains(word.substring(j,k))){
                            if (word.substring(j,k).length() > substrsize){
                                substrsize = word.substring(j,k).length();
                                substr = word.substring(j,k);
                            }
                        }
                    }
                }
                if (substrsize > 0){
                    if (wordVectorModel.similarity(substr,word)>0.7){
                        System.out.print(substr+" "+word+" ");
                        System.out.println(wordVectorModel.similarity(substr,word) + " vague hit");
                        soundEffects.add(substr);
                    }
                }
            }
        }
        return soundEffects;
    }
}

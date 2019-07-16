package yuesheng.tv.Utility;

import javafx.util.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import yuesheng.tv.Entity.BGM;
import yuesheng.tv.Entity.BGMName;
import yuesheng.tv.Entity.EmotionMap;
import yuesheng.tv.Entity.EntityKey.EmotionMapKey;
import yuesheng.tv.Repository.BGMNameRepository;
import yuesheng.tv.Repository.BGMRepository;
import yuesheng.tv.Repository.EmotionMapRepository;

import java.util.Iterator;
import java.util.List;
import java.util.Map;


@Component
public class CorrelationComputer {
    @Autowired
    BGMRepository bgmRepository;
    @Autowired
    EmotionMapRepository emotionMapRepository;
    @Autowired
    BGMNameRepository bgmNameRepository;

    String BGMPicker(Map<String,Integer> report){
        List<BGMName> BGMNames = bgmNameRepository.findAll();
        Pair<String,Integer> chosen = new Pair<String,Integer>("",1<<31);
        int Nsize = BGMNames.size();
        for(int i=0;i<Nsize;i++) {
            int score=0;
            String name = BGMNames.get(i).getName();
            List<BGM> BGMMappings = bgmRepository.findByBGMKeyname(name);
            int Msize = BGMMappings.size();
            for(int j=0;j<Msize;j++) {
                String mood = BGMMappings.get(j).getKey().getMood();
                Iterator iterator = report.entrySet().iterator();
                while (iterator.hasNext()) {
                    Map.Entry entry = (Map.Entry) iterator.next();
                    String sense = entry.getKey().toString();
                    EmotionMapKey EMK = new EmotionMapKey();
                    EMK.setMood(mood);
                    EMK.setSense(sense);
                    int relevancy = emotionMapRepository.findByEmotionMapKey(EMK).getRelevancy();
                    score+=relevancy*Integer.valueOf(entry.getValue().toString());
                }
            }
            if(score>chosen.getValue()) chosen = new Pair<String,Integer>(name,score);
        }
        return chosen.getKey();
    }
}

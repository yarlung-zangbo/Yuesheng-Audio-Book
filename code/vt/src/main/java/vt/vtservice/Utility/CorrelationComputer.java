package vt.vtservice.Utility;

import javafx.util.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import vt.vtservice.Entity.BGM;
import vt.vtservice.Entity.BGMName;
import vt.vtservice.Entity.EntityKey.EmotionMapKey;
import vt.vtservice.Repository.BGMNameRepository;
import vt.vtservice.Repository.BGMRepository;
import vt.vtservice.Repository.EmotionMapRepository;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


@Component
public class CorrelationComputer {
    @Autowired
    private BGMRepository bgmRepository;
    @Autowired
    EmotionMapRepository emotionMapRepository;
    @Autowired
    BGMNameRepository bgmNameRepository;

    public String BGMPicker(Map<String,Integer> report){
        List<BGMName> BGMNames = bgmNameRepository.findAll();
        Collections.shuffle(BGMNames);
        Pair<String,Integer> chosen = new Pair<String,Integer>("",1<<31);
        int Nsize = BGMNames.size();
        for(int i=0;i<Nsize;i++) {
            int score=0;
            String name = BGMNames.get(i).getName();
            List<BGM> BGMMappings = bgmRepository.findByKeyName(name);
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
        System.out.println(chosen.toString());
        return chosen.getKey();
    }
}

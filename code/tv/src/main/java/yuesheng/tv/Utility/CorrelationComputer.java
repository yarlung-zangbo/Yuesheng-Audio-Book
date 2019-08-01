package yuesheng.tv.Utility;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import yuesheng.tv.Entity.BGM;
import yuesheng.tv.Entity.BGMName;
import yuesheng.tv.Entity.EmotionMap;
import yuesheng.tv.Entity.EntityKey.EmotionMapKey;
import yuesheng.tv.Repository.BGMNameRepository;
import yuesheng.tv.Repository.BGMRepository;
import yuesheng.tv.Repository.EmotionMapRepository;

import java.util.*;


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
        ImmutablePair<String,Integer> chosen = new ImmutablePair<>("",1<<31);
        int Nsize = BGMNames.size();
        List<EmotionMap> EMMappings = emotionMapRepository.findAll();
        for(int i=0;i<Nsize;i++) {
            int score=0;
            String name = BGMNames.get(i).getName();
            List<BGM> BGMMappings = bgmRepository.findByKeyName(name);
            int Msize = BGMMappings.size();
            for(int j=0;j<Msize;j++) {
                String mood = BGMMappings.get(j).getKey().getMood();
                Map<EmotionMapKey,EmotionMap> moodMappings = new HashMap<>();
                int length = EMMappings.size();
                for(int k=0;k<length;k++){
                    EmotionMap EM = EMMappings.get(k);
                    if(EM.getEmotionMapKey().getMood().equals(mood)){
                        moodMappings.put(EM.getEmotionMapKey(),EM);
                    }
                }
                Iterator iterator = report.entrySet().iterator();
                while (iterator.hasNext()) {
                    Map.Entry entry = (Map.Entry) iterator.next();
                    String sense = entry.getKey().toString();
                    EmotionMapKey EMK = new EmotionMapKey();
                    EMK.setMood(mood);
                    EMK.setSense(sense);
                    int relevancy= moodMappings.get(EMK).getRelevancy();
                    score+=relevancy*Integer.valueOf(entry.getValue().toString());
                }
            }
            if(score>chosen.getValue()) chosen = new ImmutablePair<>(name,score);
        }
        System.out.println(chosen.toString());
        return chosen.getKey();
    }
}

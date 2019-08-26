package vt.vtservice.Utility;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import vt.vtservice.Entity.BGM;
import vt.vtservice.Entity.BGMName;
import vt.vtservice.Entity.EmotionMap;
import vt.vtservice.Entity.EntityKey.EmotionMapKey;
import vt.vtservice.Repository.BGMNameRepository;
import vt.vtservice.Repository.BGMRepository;
import vt.vtservice.Repository.EmotionMapRepository;

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
        List<BGM> ABGMMappings = bgmRepository.findAll();
        int mappingSize = ABGMMappings.size();
        for(int i=0;i<Nsize;i++) {
            int score=0;
            String name = BGMNames.get(i).getName();
            List<BGM> BGMMappings = new ArrayList<>();
            for(int k=0;k<mappingSize;k++){
                if(ABGMMappings.get(k).getKey().getName().equals(name)) BGMMappings.add(ABGMMappings.get(k));
            }
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

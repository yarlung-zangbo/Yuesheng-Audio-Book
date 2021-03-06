package com.yuesheng.cf.utility;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class RecList {
    public static ArrayList<Integer> GetRecList(String username,
                                                Map<String, ArrayList<Integer>> trainDict,
                                                Map<Integer, Map<Integer, Double>> recMap,
                                                int sim_limit,
                                                int rec_limit) {
        if (trainDict == null || trainDict.isEmpty() || recMap == null || recMap.isEmpty()) return null;

        Map<Integer, Double> rank_tiem = new HashMap<>();
        ArrayList<Integer> arr_item = trainDict.get(username);

        if (arr_item == null || arr_item.isEmpty()) return null;

        int cnt;
        for (Integer item : arr_item) {
            cnt = 0;
            Map<Integer, Double> recMatrix = recMap.get(item);
            if (recMatrix == null) return null;
            final Map<Integer, Double> sortedMatrix = recMatrix.entrySet()
                    .stream()
                    .sorted((Map.Entry.<Integer, Double>comparingByValue().reversed()))
                    .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
            for (Map.Entry<Integer, Double> entry : sortedMatrix.entrySet()) {
                if (arr_item.contains(entry.getKey()))
                    continue;

                rank_tiem.putIfAbsent(entry.getKey(), 0.0);
                rank_tiem.compute(entry.getKey(), (k, v) -> v + entry.getValue());

                if (cnt >= sim_limit)
                    break;
                cnt++;
            }
        }

        final Map<Integer, Double> sortedRec = rank_tiem.entrySet()
                .stream()
                .sorted((Map.Entry.<Integer, Double>comparingByValue().reversed()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));

        ArrayList<Integer> recList = new ArrayList<>();
        cnt = 0;
        for (Map.Entry<Integer, Double> entry : sortedRec.entrySet()) {
            recList.add(entry.getKey());

            if (cnt >= rec_limit)
                break;
            cnt++;
        }

        return recList;
    }
}

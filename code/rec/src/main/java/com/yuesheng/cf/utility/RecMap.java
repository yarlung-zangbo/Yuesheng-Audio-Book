package com.yuesheng.cf.utility;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RecMap {
    private static Map<String, ArrayList<Integer>> LoadData(List<Object> markList, Map<String, ArrayList<Integer>> trainDict) {
        if (markList == null) return null;

        for (Object obj : markList) {
            Object[] arr = (Object[]) obj;
            trainDict.putIfAbsent(arr[0].toString(), new ArrayList<>());
            trainDict.get(arr[0].toString()).add((Integer) arr[1]);
        }
        return trainDict;
    }

    public static Map<Integer, Map<Integer, Double>> GetRecMap(List<Object> markList,
                                                               Map<String, ArrayList<Integer>> trainDict) {
        if (markList == null || markList.isEmpty()) return null;

        LoadData(markList, trainDict);
        if (trainDict == null || trainDict.isEmpty()) return null;

        Map<Integer, Integer> itemCnt = new HashMap<>();
        Map<Integer, Map<Integer, Integer>> itemMatrix = new HashMap<>();
        Map<Integer, Map<Integer, Double>> simMatrix = new HashMap<>();

        for (Map.Entry<String, ArrayList<Integer>> entry : trainDict.entrySet()) {
            for (Integer i : entry.getValue()) {
                itemCnt.putIfAbsent(i, 0);
                itemCnt.compute(i, (k, v) -> v + 1);

                for (Integer j : entry.getValue()) {
                    if (i.intValue() == j.intValue()) continue;
                    itemMatrix.putIfAbsent(i, new HashMap<>());
                    itemMatrix.get(i).putIfAbsent(j, 0);
                    itemMatrix.get(i).compute(j, (k, v) -> v + 1);
                }
            }
        }

        for (Map.Entry<Integer, Map<Integer, Integer>> entry_left : itemMatrix.entrySet()) {
            Integer key_left = entry_left.getKey();
            for (Map.Entry<Integer, Integer> entry_right : entry_left.getValue().entrySet()) {
                simMatrix.putIfAbsent(key_left, new HashMap<>());
                simMatrix.get(key_left).put(entry_right.getKey(), entry_right.getValue() / Math.sqrt(itemCnt.get(key_left) * itemCnt.get(entry_right.getKey())));
            }
        }

        return simMatrix;
    }
}

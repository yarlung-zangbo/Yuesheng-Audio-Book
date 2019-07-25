package com.yuesheng.cf.serviceimpl;

import com.yuesheng.cf.service.RecService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.yuesheng.cf.utility.RecMap.GetRecMap;
import static com.yuesheng.cf.utility.RecList.GetRecList;

import com.yuesheng.cf.dao.MarkDao;

@Service
public class RecServiceImpl implements RecService {

    @Autowired
    private MarkDao markDao;

    private static final int mark_limit = 2;
    private static final int sim_limit = 20;
    private static final int rec_limit = 20;
    private static Map<Integer, Map<Integer, Double>> recMap;
    private static Map<String, ArrayList<Integer>> trainDict;

    @Override
    public void updateRecMap() {
        trainDict = new HashMap<>();
        List<Object> markList = markDao.getAllMarkRecord(mark_limit);
        recMap = GetRecMap(markList, trainDict);
        System.out.println(recMap);
    }

    @Override
    public ArrayList<Integer> getRecList(String username) {
        return GetRecList(username, trainDict, recMap, sim_limit, rec_limit);
    }
}

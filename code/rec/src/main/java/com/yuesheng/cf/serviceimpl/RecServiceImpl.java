package com.yuesheng.cf.serviceimpl;

import com.yuesheng.cf.dao.MarkDao;
import com.yuesheng.cf.dao.SoundbookDao;
import com.yuesheng.cf.dao.UserDao;
import com.yuesheng.cf.service.RecService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.yuesheng.cf.utility.RecList.GetRecList;
import static com.yuesheng.cf.utility.RecMap.GetRecMap;

@Service
public class RecServiceImpl implements RecService {

    @Autowired
    private MarkDao markDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private SoundbookDao soundbookDao;

    private static final int mark_limit = 3;
    private static final int sim_limit = 20;
    private static final int rec_limit = 20;
    private static final int top_limit = 20;

    private static ArrayList<Integer> rankList;
    private static Map<Integer, Map<Integer, Double>> recMap;
    private static Map<String, ArrayList<Integer>> trainDict;

    @Override
    public void updateRecMap() {
        trainDict = new HashMap<>();
        List<Object> markList = markDao.getAllMarkRecord(mark_limit);
        rankList = soundbookDao.getMarkRankList(top_limit);
        recMap = GetRecMap(markList, trainDict);
    }

    @Override
    public ArrayList<Integer> getRecList(String username) {
        if (rankList == null) return null;
        if (rankList.size() < rec_limit) return rankList;

        ArrayList<Integer> recList = GetRecList(username, trainDict, recMap, sim_limit, rec_limit);
        if (recList == null) {
            recList = new ArrayList<>();
            for (int i = 0; i < rec_limit; i++) {
                recList.add(rankList.get(i));
            }
        } else {
            int left_size = rec_limit - recList.size(), cnt = 0;
            while (cnt != left_size)
                recList.add(cnt++);
        }

        return recList;
    }

}

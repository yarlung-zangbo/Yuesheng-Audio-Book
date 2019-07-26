package com.yuesheng.cf.daoimpl;

import com.yuesheng.cf.dao.SoundbookDao;
import com.yuesheng.cf.entity.Soundbook;
import com.yuesheng.cf.repository.SoundbookRepository;
import com.yuesheng.cf.tool.TimeTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class SoundbookDaoImpl implements SoundbookDao {

    @Autowired
    SoundbookRepository soundbookRepository;

    @Override
    public Soundbook findByBookid(int bookid) {
        return soundbookRepository.findByBookid(bookid);
    }

    @Override
    public List<Soundbook> findReleasedBookByName(String name) {
        return soundbookRepository.findByReleasetimeLessThanAndNameContaining(TimeTool.now(), name);
    }

    @Override
    public ArrayList<Integer> getMarkRankList(int limit) {
        List<Object> mark_list = soundbookRepository.getMarkRankList(limit);

        if (mark_list == null) return null;

        ArrayList<Integer> format_list = new ArrayList<>();
        for (Object obj : mark_list) {
            format_list.add((Integer) obj);
        }
        return format_list;
    }
}

package com.yuesheng.cf.dao;


import com.yuesheng.cf.entity.Mark;
import com.yuesheng.cf.entity.Soundbook;
import com.yuesheng.cf.entity.User;

import java.util.List;

public interface MarkDao {
    public Mark getMark(User user, Soundbook book);

    public Mark save(Mark mark);

    public float caculateMark(int bookid);

    public List<Object> getAllMarkRecord(int limit);
}

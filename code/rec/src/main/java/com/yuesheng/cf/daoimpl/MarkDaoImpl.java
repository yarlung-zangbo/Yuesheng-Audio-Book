package com.yuesheng.cf.daoimpl;

import com.yuesheng.cf.dao.MarkDao;
import com.yuesheng.cf.entity.Mark;
import com.yuesheng.cf.entity.Soundbook;
import com.yuesheng.cf.entity.User;
import com.yuesheng.cf.repository.MarkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class MarkDaoImpl implements MarkDao {

    @Autowired
    public MarkRepository markRepository;

    @Override
    public Mark getMark(User user, Soundbook book) {
        return markRepository.findByUserAndSoundbook(user, book);
    }

    @Override
    public Mark save(Mark mark) {
        return markRepository.saveAndFlush(mark);
    }

    @Override
    public float caculateMark(int bookid) {
        return markRepository.caculateMark(bookid);
    }

    @Override
    public List<Object> getAllMarkRecord(int limit) {
        return markRepository.getAllMarkRecord(limit);
    }
}

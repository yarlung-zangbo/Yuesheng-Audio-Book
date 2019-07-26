package com.yuesheng.cf.repository;

import com.yuesheng.cf.entity.Mark;
import com.yuesheng.cf.entity.Soundbook;
import com.yuesheng.cf.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface MarkRepository extends JpaRepository<Mark, Integer> {
    public Mark findByUserAndSoundbook(User user, Soundbook book);

    @Query(value = "select avg(score) from mark where bookid=?1", nativeQuery = true)
    public float caculateMark(int bookid);

    @Query(value = "select username,bookid,score from mark where score>=?1", nativeQuery = true)
    public List<Object> getAllMarkRecord(int limit);
}

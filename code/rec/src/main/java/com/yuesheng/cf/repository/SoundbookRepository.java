package com.yuesheng.cf.repository;


import com.yuesheng.cf.entity.Soundbook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.ArrayList;
import java.util.List;

public interface SoundbookRepository extends JpaRepository<Soundbook, Integer> {
    public Soundbook findByBookid(int bookid);

    public List<Soundbook> findByReleasetimeLessThanAndNameContaining(String time, String name);

    @Query(value = "select bookid from soundbook where releasetime < now() order by mark desc limit ?1", nativeQuery = true)
    public ArrayList<Object> getMarkRankList(int limit);
}

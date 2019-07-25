package com.yuesheng.cf.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import com.yuesheng.cf.entity.Soundbook;

import java.util.List;

public interface SoundbookRepository extends JpaRepository<Soundbook, Integer> {
    public Soundbook findByBookid(int bookid);

    public List<Soundbook> findByReleasetimeLessThanAndNameContaining(String time, String name);
}

package com.yuesheng.cf.dao;


import com.yuesheng.cf.entity.Soundbook;

import java.util.List;

public interface SoundbookDao {
    public Soundbook findByBookid(int bookid);

    public List<Soundbook> findReleasedBookByName(String name);
}

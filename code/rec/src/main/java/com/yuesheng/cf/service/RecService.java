package com.yuesheng.cf.service;

import com.yuesheng.cf.entity.Soundbook;

import java.util.ArrayList;

public interface RecService {
    public void updateRecMap();

    public ArrayList<Soundbook> getRecList(String username);
}

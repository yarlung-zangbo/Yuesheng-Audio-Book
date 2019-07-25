package com.yuesheng.cf.tool;


import com.yuesheng.cf.entity.Soundbook;

public class CheckBook {
    public static String check(Soundbook book) {
        if (book.getDisabled().compareTo(TimeTool.now()) > 0)
            return "book disabled";
        if (book.getReleasetime() == null)
            return "book not release";
        return null;
    }
}

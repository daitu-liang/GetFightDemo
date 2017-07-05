package com.daitu_liang.study.mytest.entity;

/**
 * Created by leixiaoliang on 2017/7/4.
 * 邮箱：lxliang1101@163.com
 */

public class HabitInfo {

    public String getFreeStyle() {
        return freeStyle;
    }

    public void setFreeStyle(String freeStyle) {
        this.freeStyle = freeStyle;
    }

    private String freeStyle;
    public HabitInfo(String freeStyle) {
        this.freeStyle = freeStyle;
    }
}

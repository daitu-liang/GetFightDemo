package com.daitu_liang.study.mytest.entity;

/**
 * Created by leixiaoliang on 2017/7/4.
 * 邮箱：lxliang1101@163.com
 */

public class UserInfo {
    private  HabitInfo habitInfo=new HabitInfo();
    public String getHabit(){//委托函数
       return habitInfo.getHabit();
    }
}

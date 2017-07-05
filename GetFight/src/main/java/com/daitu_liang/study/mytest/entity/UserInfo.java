package com.daitu_liang.study.mytest.entity;

/**
 * Created by leixiaoliang on 2017/7/4.
 * 邮箱：lxliang1101@163.com
 */

public class UserInfo  {
    HabitInfo mHabitInfo;
    //转型构造函数则是对其实例变量的赋值而已
    public UserInfo (HabitInfo habitInfo){
        mHabitInfo=habitInfo;
    }
    //添加委托函数
}

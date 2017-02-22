package com.daitu_liang.study.mytest.test;

/**
 * Created by leixiaoliang on 2017/2/22.
 */
public class TestUnitImp implements ITestUnit {
    @Override
    public int getTest(int a, int b) {
        int c=a+b;
        return  c;
    }

    @Override
    public String getTestString(String a, String b) {
        return a+b;
    }
}

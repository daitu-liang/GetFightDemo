package com.daitu_liang.study.mytest;

import android.test.InstrumentationTestCase;

import com.daitu_liang.study.mytest.unittest.ITestUnit;
import com.daitu_liang.study.mytest.unittest.TestUnitImp;

/**
 * Created by leixiaoliang on 2017/2/22.
 */
public class TestData extends InstrumentationTestCase {
    public void testAdd()throws Exception{

        ITestUnit iTestUnit=new TestUnitImp();
        assertEquals(4, iTestUnit.getTest(1,2));
    }
    public void test()throws Exception{
        final int expected = 5;
        final int reality = 5;
        assertFalse("fff",expected!=reality);
    }
}

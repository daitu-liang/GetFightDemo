package com.daitu_liang.study.mytest;

import android.test.InstrumentationTestCase;

import com.daitu_liang.study.mytest.test.TestUnitImp;

/**
 * Created by leixiaoliang on 2017/2/22.
 */
public class AppleTest extends InstrumentationTestCase {
    private TestUnitImp iTestUnit;

    public void setUp() throws Exception {
        super.setUp();
        iTestUnit=new TestUnitImp();
    }

    public void testGetTest() throws Exception {

        assertEquals(5,iTestUnit.getTest(4,1));
    }

    public void testGetTestString() throws Exception {

        assertEquals("helloworld", iTestUnit.getTestString("hello","world"));
    }
}

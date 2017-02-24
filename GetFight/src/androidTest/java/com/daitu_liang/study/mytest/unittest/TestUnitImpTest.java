package com.daitu_liang.study.mytest.unittest;

import junit.framework.TestCase;

/**
 * Created by leixiaoliang on 2017/2/22.
 */
public class TestUnitImpTest extends TestCase {
    private TestUnitImp iTestUnit;

    public void testAutoAdd()throws Exception{

        ITestUnit iTestUnit=new TestUnitImp();
        assertEquals(4, iTestUnit.getTest(2,2));
    }

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
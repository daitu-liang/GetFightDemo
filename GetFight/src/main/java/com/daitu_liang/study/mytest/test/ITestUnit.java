package com.daitu_liang.study.mytest.test;

/**
 * android studio 2.0进行单元测试，
 * 1.src下目录，androidTest是进行Android设备上进行单元测试，
 * test目录是进行本地运行，也是测试java的；
 * 2.点击左下角侧面，Build Variants，设置Test Artifact,
 * Android测试用例选择Android Instrumentation Tests;
 * java测试选项框选择Unit Tests；
 * 3.找不到不到Test Artifact解决方案是依次打开：
 * File Menu -> Settings -> Build, Execution, Deployment -> Build Tools -> Gradle -> Experimental，
 将Enable all test artifacts (Unit Test and Instrumentation Test) in Android projects取消勾选，
 之后我们熟悉的Test Artifact就出现了
 4.测试的方法必须以test开头，正确的会高亮
 * Created by leixiaoliang on 2017/2/22.
 */
public interface ITestUnit {
    int getTest(int a,int b);
    String getTestString(String a,String b);
}

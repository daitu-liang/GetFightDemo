package com.daitu_liang.study.mytest.util.otto;

import com.squareup.otto.Bus;

/**
 * Created by leixiaoliang on 2017/1/16.
 */
public class BusProvider {

    private BusProvider(){}
    public static Bus getInstance(){
        return Otto.bus;
    }

    private static class Otto{
       private static final Bus bus=new Bus();
    }
}

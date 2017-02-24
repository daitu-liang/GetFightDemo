package com.daitu_liang.study.mytest.unittest;

import android.content.Intent;

/**
 * Created by leixiaoliang on 2017/2/24.
 */
public class ServiceTestCase extends android.test.ServiceTestCase<ServiceTest>{

    private ServiceTest mService;

    public ServiceTestCase() {
        super(ServiceTest.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        Intent intent=new Intent(mContext,ServiceTest.class);
        intent.putExtra("a",1);
        intent.putExtra("b",3);
        startService(intent);
        mService=getService();
        assertNotNull(mService);
    }
    public void testAdd(){
            assertEquals(4,mService.addResult());
    }
}

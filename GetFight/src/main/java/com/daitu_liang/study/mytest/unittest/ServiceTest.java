package com.daitu_liang.study.mytest.unittest;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

/**
 * Created by leixiaoliang on 2017/2/24.
 */
public class ServiceTest extends Service {
    private int aVaule;
    private int bVaule;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        aVaule=intent.getIntExtra("a",0);
        bVaule=intent.getIntExtra("b",0);
        return super.onStartCommand(intent, flags, startId);
    }
    public int addResult(){
        return aVaule+bVaule;
    }

}

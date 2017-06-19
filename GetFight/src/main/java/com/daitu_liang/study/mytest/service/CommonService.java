package com.daitu_liang.study.mytest.service;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.daitu_liang.study.mytest.util.Log;

/**
 * Created by leixiaoliang on 2017/6/16.
 * 邮箱：lxliang1101@163.com
 */

public class CommonService extends Service{
    private final static int SERVICE_ID = 1000;
    private static final String TAG ="CommonService" ;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
    public void onCreate() {
        super.onCreate();
        Log.getLogger(TAG).i(TAG, "onCreate");
        handler.postDelayed(runnable, 2000);//每2s执行一次runnable
    }
    final Handler handler = new Handler();
    final Runnable runnable = new Runnable() {
        @Override
        public void run() {
            Log.getLogger(TAG).i(TAG, "CommonService还活着.....");
            handler.postDelayed(this, 2000);
        }
    };
    /**
     * 让service进行保活，同时通知栏不会出现Notification
     * @param intent
     * @param flags
     * @param startId
     * @return
     */
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        return super.onStartCommand(intent, flags, startId);
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.getLogger(TAG).i(TAG, "CommonService---over.....");
    }
}

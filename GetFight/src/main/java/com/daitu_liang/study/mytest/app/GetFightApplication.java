package com.daitu_liang.study.mytest.app;

import android.app.Application;
import android.content.Context;

import com.daitu_liang.study.mytest.util.PreferencesManager;

/**
 * Created by leixiaoliang on 2017/1/10.
 */
public class GetFightApplication extends Application {
    public static Context CONTEXT;
    public static PreferencesManager preferenceManager;

    @Override
    public void onCreate() {
        super.onCreate();
        setContext(this);
        init();
    }
    private void init() {
        preferenceManager = PreferencesManager.getInstance(this);

    }
    private static void setContext(Context mContext) {
        CONTEXT = mContext;
    }

    public static Context getContext() {
        return CONTEXT;
    }
    public static PreferencesManager getPreferenceManager() {
        return preferenceManager;
    }
}

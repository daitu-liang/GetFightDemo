package com.daitu_liang.study.mytest.app;

import android.app.Application;
import android.content.Context;

import com.daitu_liang.study.mytest.util.PreferencesManager;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

import org.litepal.LitePal;

/**
 * Created by leixiaoliang on 2017/1/10.
 */
public class GetFightApplication extends Application {
    public static Context CONTEXT;
    public static PreferencesManager preferenceManager;
    public static RefWatcher getRefWatcher(Context context) {
        GetFightApplication application = (GetFightApplication) context.getApplicationContext();
        return application.refWatcher;
    }

    private RefWatcher refWatcher;
    @Override
    public void onCreate() {
        super.onCreate();
        setContext(this);
        init();
    }
    private void init() {

        preferenceManager = PreferencesManager.getInstance(this);
        refWatcher = LeakCanary.install(this);
        Fresco.initialize(this);
        LitePal.initialize(this);
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

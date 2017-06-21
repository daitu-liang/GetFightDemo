package com.daitu_liang.study.mytest.app;

import android.annotation.TargetApi;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.multidex.MultiDex;

import com.daitu_liang.study.mytest.util.PreferencesManager;
import com.daitu_liang.study.mytest.util.tinker.MyLogImp;
import com.daitu_liang.study.mytest.util.tinker.SampleApplicationContext;
import com.daitu_liang.study.mytest.util.tinker.TinkerManager;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;
import com.tencent.tinker.anno.DefaultLifeCycle;
import com.tencent.tinker.lib.tinker.Tinker;
import com.tencent.tinker.lib.tinker.TinkerInstaller;
import com.tencent.tinker.loader.app.DefaultApplicationLike;

import org.litepal.LitePal;

import static com.tencent.tinker.loader.shareutil.ShareConstants.TINKER_ENABLE_ALL;

/**
 * Created by leixiaoliang on 2017/1/10.
 */

@SuppressWarnings("unused")
@DefaultLifeCycle(
        application = "com.daitu_liang.study.mytest.app.GetFightApplication",//application类名
        flags = TINKER_ENABLE_ALL, //tinkerFlags
        loadVerifyFlag = false)
public class GetFightApplicationTinker extends DefaultApplicationLike {
    public static Context CONTEXT;
    public static PreferencesManager preferenceManager;
    public static RefWatcher getRefWatcher(Context context) {
        return refWatcher;
    }
    private static final String TAG = "GetFightApplicationTinker";
    public GetFightApplicationTinker(Application application,
                                     int tinkerFlags,
                                     boolean tinkerLoadVerifyFlag,
                                     long applicationStartElapsedTime,
                                     long applicationStartMillisTime, Intent tinkerResultIntent) {
        super(application, tinkerFlags, tinkerLoadVerifyFlag,
                applicationStartElapsedTime, applicationStartMillisTime, tinkerResultIntent);
    }
    /**
     * install multiDex before install tinker
     * so we don't need to put the tinker lib classes in the main dex
     *
     * @param base
     */
    @TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
    @Override
    public void onBaseContextAttached(Context base) {
        super.onBaseContextAttached(base);
        //you must install multiDex whatever tinker is installed!
        MultiDex.install(base);
        SampleApplicationContext.application = getApplication();
        SampleApplicationContext.context = getApplication();
        TinkerManager.setTinkerApplicationLike(this);
        TinkerManager.initFastCrashProtect();
        //should set before tinker is installed
        TinkerManager.setUpgradeRetryEnable(true);
        //optional set logIml, or you can use default debug log
        TinkerInstaller.setLogIml(new MyLogImp());

        //installTinker after load multiDex
        //or you can put com.tencent.tinker.** to main dex
        TinkerManager.installTinker(this);
        Tinker tinker = Tinker.with(getApplication());
        setContext(this.getApplication());
    }
    @TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
    public void registerActivityLifecycleCallbacks(Application.ActivityLifecycleCallbacks callback) {
        getApplication().registerActivityLifecycleCallbacks(callback);
    }

    private  static RefWatcher refWatcher;

    @Override
    public void onCreate() {
        super.onCreate();
        init();
    }
    private void init() {
        preferenceManager = PreferencesManager.getInstance(this.getApplication());
        refWatcher = LeakCanary.install(this.getApplication());
        Fresco.initialize(this.getApplication());
        LitePal.initialize(this.getApplication());
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

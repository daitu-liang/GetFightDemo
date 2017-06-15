package com.daitu_liang.study.mytest.app;

import android.content.Context;

import com.daitu_liang.study.mytest.util.PreferencesManager;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;
import com.tencent.tinker.loader.app.TinkerApplication;
import com.tencent.tinker.loader.shareutil.ShareConstants;

import org.litepal.LitePal;

/**
 * Created by leixiaoliang on 2017/1/10.
 */


public class GetFightApplication extends TinkerApplication {
    public static Context CONTEXT;
    public static PreferencesManager preferenceManager;

    public static RefWatcher getRefWatcher(Context context) {
        return refWatcher;
    }

    private static final String TAG = "GetFightApplication";

    public GetFightApplication() {
        super(
                //tinkerFlags, which types is supported
                //dex only, library only, all support
                ShareConstants.TINKER_ENABLE_ALL,
                // This is passed as a string so the shell application does not
                // have a binary dependency on your ApplicationLifeCycle class.
                "com.daitu_liang.study.mytest.app.SampleApplicationLike");
    }

    private  static RefWatcher refWatcher;

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

package com.daitu_liang.study.mytest;

import android.app.Activity;
import android.os.Bundle;

import com.daitu_liang.study.mytest.app.GetFightApplication;
import com.squareup.leakcanary.RefWatcher;

public class WaveDemoActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wave_demo_layout);
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        RefWatcher refWatcher = GetFightApplication.getRefWatcher(this);
        refWatcher.watch(this);
    }
}

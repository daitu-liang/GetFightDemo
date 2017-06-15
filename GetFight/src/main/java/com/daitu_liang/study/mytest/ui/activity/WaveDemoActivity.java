package com.daitu_liang.study.mytest.ui.activity;

import android.app.Activity;
import android.os.Bundle;

import com.daitu_liang.study.mytest.R;
import com.daitu_liang.study.mytest.app.GetFightApplicationTinker;
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
        RefWatcher refWatcher = GetFightApplicationTinker.getRefWatcher(this);
        refWatcher.watch(this);
    }
}

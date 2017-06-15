package com.daitu_liang.study.mytest.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.daitu_liang.study.mytest.R;
import com.daitu_liang.study.mytest.app.GetFightApplicationTinker;
import com.daitu_liang.study.mytest.paint.SnowView;
import com.squareup.leakcanary.RefWatcher;

public class SnowActivity extends AppCompatActivity {

    private SnowView startBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_);
         startBtn = (SnowView) findViewById(R.id.float_btn);
        startBtn.startAnimationFloat();
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        RefWatcher refWatcher = GetFightApplicationTinker.getRefWatcher(this);
        refWatcher.watch(this);
        if(startBtn!=null){
            startBtn=null;
        }
    }
}

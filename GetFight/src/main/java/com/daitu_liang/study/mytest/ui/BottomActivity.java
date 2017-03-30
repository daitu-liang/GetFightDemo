package com.daitu_liang.study.mytest.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.daitu_liang.study.mytest.R;
import com.daitu_liang.study.mytest.app.GetFightApplication;
import com.daitu_liang.study.mytest.paint.BottomLineView;
import com.squareup.leakcanary.RefWatcher;

import butterknife.ButterKnife;

public class BottomActivity extends AppCompatActivity {


    BottomLineView bottomView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom);
        ButterKnife.bind(this);
        bottomView=(BottomLineView)findViewById(R.id.bottom_view);

        bottomView.starRun();
    }
    @Override public void onDestroy() {
        super.onDestroy();
        RefWatcher refWatcher = GetFightApplication.getRefWatcher(this);
        refWatcher.watch(this);
    }
}

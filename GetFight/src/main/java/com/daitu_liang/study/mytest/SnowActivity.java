package com.daitu_liang.study.mytest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.daitu_liang.study.mytest.paint.SnowView;

public class SnowActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_);
        SnowView startBtn = (SnowView) findViewById(R.id.float_btn);
        startBtn.startAnimationFloat();
    }
}

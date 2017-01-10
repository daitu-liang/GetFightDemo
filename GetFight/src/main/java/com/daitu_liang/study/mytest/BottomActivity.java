package com.daitu_liang.study.mytest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.daitu_liang.study.mytest.paint.BottomLineView;

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
}

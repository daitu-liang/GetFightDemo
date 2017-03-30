package com.daitu_liang.study.mytest.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.daitu_liang.study.mytest.paint.GradientView;

public class PaintActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_paint);
//        setContentView(new TestPaintView(this));
        setContentView(new GradientView(this));
    }
}
